package com.ssy.api.servicetype;

import com.ssy.api.entity.table.ln.LnaLoan;

import java.util.List;

/**
 * @Description 贷款相关服务
 * @Author sunshaoyu
 * @Date 2020/9/24-13:48
 */
public interface LoanService {

    /**
     * @Description 检查账户余额和会计事件余额
     * @Author sunshaoyu
     * @Date 2020/10/9-13:35
     * @return java.util.List<java.lang.String>
     */
    public List<String> checkLoanBalance();

    /**
     * @Description 获取序号的哈希值
     * @Author sunshaoyu
     * @Date 2020/10/9-13:39
     * @param upperLimit
     * @param sequence
     * @return java.lang.Long
     */
    public Long getGroupHashValue(Integer upperLimit, String sequence);

    /**
     * @Description 查询借据列表
     * @Author sunshaoyu
     * @Date 2020/10/13-11:01
     * @return java.util.List<com.ssy.api.entity.table.ln.LnaLoan>
     */
    public List<LnaLoan> queryLoanList();
}
