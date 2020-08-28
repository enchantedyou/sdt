package com.ssy.api.logic.builder;

import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.enums.E_TRANKIND;
import com.ssy.api.entity.table.local.SdpModuleMapping;
import com.ssy.api.logic.local.SdFlowtranParser;
import com.ssy.api.meta.flowtran.Flowtran;
import com.ssy.api.servicetype.ModuleMapService;
import com.ssy.api.utils.system.BizUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description 脚本生成
 * @Author sunshaoyu
 * @Date 2020年08月11日-16:36
 */
@Component
@Slf4j
public class SdScriptBuilder {

    private static ModuleMapService moduleMapService;
    @Autowired
    public void setModuleMapService(ModuleMapService moduleMapService) {
        SdScriptBuilder.moduleMapService = moduleMapService;
    }

    /**
     * @Description 构建交易参数
     * @Author sunshaoyu
     * @Date 2020/8/11-17:09
     * @param flowtranId
     * @return java.lang.String
     */
    public static String buildTransaction(String flowtranId){
        BizUtil.fieldNotNull(flowtranId, SdtDict.A.flowtran_id.getId(), SdtDict.A.flowtran_id.getLongName());
        SdpModuleMapping moduleMapping = moduleMapService.getModuleMapping(flowtranId.substring(0, 2));
        Flowtran flowtran = SdFlowtranParser.load(flowtranId);
        StringBuffer buffer = new StringBuffer();

        buffer.append("delete from msp_transaction where trxn_code = '"+flowtranId+"';").append("\r\n");
        buffer.append("delete from tsp_service_in where inner_service_code = '"+flowtranId+"';").append("\r\n");
        buffer.append("INSERT INTO msp_transaction (TRXN_CODE, TRXN_TYPE, TRXN_DESC, ALLOW_REVERSAL, FLOW_TRXN_ID, REGISTER_PACKET_IND, OVER_TIME, LOG_LEVEL, ENABLE_IND, GLOBAL_PARM_MNTN_IND, REVERSAL_IND, REGISTER_TRXN_IND, DB_TRXN_SPREAD_TYPE, READ_WRITE_SEPARAIT,  DATA_CREATE_USER, DATA_CREATE_TIME, DATA_UPDATE_USER, DATA_UPDATE_TIME, DATA_VERSION) VALUES('"+flowtranId+"','"+flowtran.getKind().getValue()+"','"+flowtran.getLongName()+"','N','"+flowtranId+"','Y',null,'error','Y','"+ (flowtran.getKind() == E_TRANKIND.P ? "Y" : "N") +"','N','"+ (flowtran.getKind() == E_TRANKIND.F ? "Y" : "N") +"','Required','N','S####','20991231','S####','20991231','1');").append("\r\n");
        buffer.append("INSERT INTO tsp_service_in (system_code,sub_system_code,out_service_code,inner_service_code,inner_service_impl_code,description,service_category,route_keys,service_type,protocol_id,is_enable,transaction_mode,log_level,timeout,alias_mapping,force_unused_odb_cache,register_mode) values('"+moduleMapping.getSystemCode()+"','"+moduleMapping.getSubSystemCode()+"','"+moduleMapService.getServiceCode(flowtranId)+"','"+flowtranId+"','*','"+flowtran.getLongName()+"','T','','','rpc','1','A','','0','0','"+(flowtran.getKind() == E_TRANKIND.P ? "1" : "0")+"','"+(flowtran.getKind() == E_TRANKIND.F ? "3" : "0")+"');").append("\r\n");
        buffer.append("commit;");
        return buffer.toString();
    }
}
