package com.ssy.api.serviceimpl;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.ssy.api.dao.mapper.ap.ApsAccountingEventMapper;
import com.ssy.api.dao.mapper.ln.LnaBalanceMapper;
import com.ssy.api.dao.mapper.ln.LnaLoanMapper;
import com.ssy.api.dao.mapper.ln.LnpBalAttributesMapper;
import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.table.ap.ApsAccountingEvent;
import com.ssy.api.entity.table.ln.LnaBalance;
import com.ssy.api.entity.table.ln.LnaLoan;
import com.ssy.api.entity.table.ln.LnpBalAttributes;
import com.ssy.api.exception.SdtException;
import com.ssy.api.factory.odb.OdbFactory;
import com.ssy.api.plugins.DBContextHolder;
import com.ssy.api.servicetype.LoanService;
import com.ssy.api.utils.business.SdtBusiUtil;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 贷款相关服务实现
 * @Author sunshaoyu
 * @Date 2020年09月24日-13:50
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS)
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LnaLoanMapper lnaLoanMapper;
    @Autowired
    private LnpBalAttributesMapper balAttributesMapper;
    @Autowired
    private LnaBalanceMapper lnaBalanceMapper;
    @Autowired
    private ApsAccountingEventMapper accountingEventMapper;
    @Autowired
    private SdtContextConfig contextConfig;

    /** 将被忽略的余额代码 **/
    private static final String IGNORE_BALCODE = "AC";

    @Override
    public List<String> checkLoanBalance() {
        //检查数据源的合法性
        List<String> errorLoanList = new ArrayList<>();
        String dataSource = DBContextHolder.getCurrentDataSource();
        if(CommUtil.isNull(dataSource)){
            throw new SdtException("The current data source is not a valid remote data source");
        }
        DruidDataSource currentDataSource = (DruidDataSource) OdbFactory.getDataSourceMap().get(dataSource);
        if(CommUtil.isNull(currentDataSource) || !currentDataSource.getUrl().contains("ln")){
            throw new SdtException("The current data source[{}] is illegal, it is not a valid loan data source", dataSource);
        }

        //加载贷款余额属性参数
        /** LN01, E_DEBITCREDIT **/
        Map<String, LnpBalAttributes> balAttributesGlMap = new HashMap<>();
        List<LnpBalAttributes> balAttributesList = balAttributesMapper.selectAll_odb1(contextConfig.getBusiOrgId(), true);

        for(LnpBalAttributes balAttributes : balAttributesList){
            LnpBalAttributes existValue = balAttributesGlMap.get(balAttributes.getBalAttributesGl());
            if(CommUtil.isNull(existValue)){
                balAttributesGlMap.put(balAttributes.getBalAttributesGl(), balAttributes);
            }
        }

        //借据账务余额检查
        List<LnaLoan> loanList = lnaLoanMapper.selectAll_odb1(contextConfig.getBusiOrgId());
        for(LnaLoan lnaLoan : loanList){
            List<ApsAccountingEvent> accountingEventList = accountingEventMapper.selectAll_odb1(lnaLoan.getLoanNo(), contextConfig.getBusiOrgId());
            List<LnaBalance> balanceList = lnaBalanceMapper.selectAll_odb1(lnaLoan.getLoanNo(), contextConfig.getBusiOrgId());
            Map<String, BigDecimal> accountingBalMap = new HashMap<>();
            Map<String, BigDecimal> balanceBalMap = new HashMap<>();

            //汇总会计事件余额
            for(ApsAccountingEvent accountingEvent : accountingEventList){
                String debitCredit = accountingEvent.getDebitCredit();
                String balAttr = accountingEvent.getBalAttributes();
                BigDecimal trxnAmt = accountingEvent.getTrxnAmt();

                if(balAttributesGlMap.get(balAttr).getBalCode().startsWith(IGNORE_BALCODE)){
                    continue;
                }

                //获取存量余额
                BigDecimal bal = accountingBalMap.get(balAttr);
                if(CommUtil.isNull(bal)){
                    accountingBalMap.put(balAttr, trxnAmt);
                }else{
                    //同向相加反向相减
                    accountingBalMap.put(balAttr, (CommUtil.equals(debitCredit, balAttributesGlMap.get(balAttr).getDebitCredit())) ? bal.add(trxnAmt) : bal.subtract(trxnAmt));
                }
            }

            //汇总账户余额
            if(CommUtil.isNull(balanceList)){
                log.warn("Loan number[{}] is dirty data for data source[{}]", lnaLoan.getLoanNo(), currentDataSource.getUrl());
            }else{
                for(LnaBalance lnaBalance : balanceList){
                    String balAttr = lnaBalance.getBalAttributesGl();
                    if(lnaBalance.getBalCode().startsWith(IGNORE_BALCODE)){
                        continue;
                    }

                    //获取存量余额
                    BigDecimal bal = balanceBalMap.get(balAttr);
                    if(CommUtil.isNull(bal)){
                        balanceBalMap.put(balAttr, lnaBalance.getBalanceAmount());
                    }else{
                        balanceBalMap.put(balAttr, bal.add(lnaBalance.getBalanceAmount()));
                    }
                }
            }

            //开始校对余额
            if(accountingBalMap.size() != balanceBalMap.size()){
                errorLoanList.add(lnaLoan.getLoanNo());
                log.warn("The number of balance attributes[{}] in the accounting event is inconsistent with the number of attributes of the loan balance[{}]", accountingBalMap.size(), balanceBalMap.size());
            }else{
                for(Map.Entry<String, BigDecimal> entry : accountingBalMap.entrySet()){
                    BigDecimal accountingBal = entry.getValue();
                    BigDecimal balanceBal = balanceBalMap.get(entry.getKey());

                    if(!CommUtil.equals(accountingBal, balanceBal)){
                        errorLoanList.add(lnaLoan.getLoanNo());
                        log.warn("The amount[{}] corresponding to the accounting event corresponding to the balance attribute[{}] of the loan[{}] is inconsistent with the amount[{}] in the balance table", accountingBal, entry.getKey(), lnaLoan.getLoanNo(), balanceBal);
                        break;
                    }
                }
            }

            if(errorLoanList.contains(lnaLoan.getLoanNo())){
                log.info("Check the balance of the loan[{}] failed", lnaLoan.getLoanNo());
            }else{
                log.info("Check the balance of the loan[{}] successfully", lnaLoan.getLoanNo());
            }
        }
        return errorLoanList;
    }

    @Override
    public Long getGroupHashValue(Integer upperLimit, String sequence) {
        BizUtil.fieldNotNull(upperLimit, SdtDict.A.upper_limit.getId(), SdtDict.A.upper_limit.getLongName());
        BizUtil.fieldNotNull(sequence, SdtDict.A.sequence.getId(), SdtDict.A.sequence.getLongName());

        int len = String.valueOf(upperLimit).length() << 1;
        if(sequence.length() > len){
            sequence = sequence.substring(sequence.length() - len);
        }
        //序号转数值
        if(!CommUtil.isInteger(sequence)){
            StringBuilder builder = new StringBuilder(32);
            char[] charArray = sequence.toCharArray();
            for(char c : charArray){
                if(!Character.isDigit(c)){
                    int num = c - 65;
                    if(num < 0){
                        num = 0;
                    }
                    num = num % 10;
                    builder.append(num);
                }else{
                    builder.append(c);
                }
            }
            sequence = builder.toString();
        }
        return Long.valueOf(sequence) % upperLimit + 1;
    }

    @Override
    public List<LnaLoan> queryLoanList() {
        return lnaLoanMapper.selectAll_odb1(contextConfig.getBusiOrgId());
    }
}
