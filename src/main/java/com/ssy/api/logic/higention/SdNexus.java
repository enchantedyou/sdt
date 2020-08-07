package com.ssy.api.logic.higention;

import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.enums.E_BUSIMODULE;
import com.ssy.api.entity.table.local.SdpModuleMapping;
import com.ssy.api.exception.SdtException;
import com.ssy.api.servicetype.ModuleMapService;
import com.ssy.api.utils.parse.XmlParser;
import com.ssy.api.utils.system.BizUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 私服nexus处理包
 * @Author sunshaoyu
 * @Date 2020年07月31日-11:16
 */
@Slf4j
@Component
public class SdNexus {

    /** 仓库类型 **/
    private static final String[] REPOSITORY_TYPE = {"odc-dev", "odc-pub", "odc-public", "odc-test", "public"};
    /** nexus版本信息url **/
    public static final String NEXUS_VER_FORMAT = "http://nexus.odc.sunline.cn/repository/%s/cn/sunline/icore/%s/%s-%s/maven-metadata.xml";

    private static ModuleMapService moduleMapService;
    @Autowired
    public void setModuleMapService(ModuleMapService moduleMapService) {
        SdNexus.moduleMapService = moduleMapService;
    }

    /**
     * @Description 获取nexus仓库类型
     * @Author sunshaoyu
     * @Date 2020/8/5-14:26
     * @return java.lang.String[]
     */
    public static String[] getRepositoryTypes(){
        return REPOSITORY_TYPE;
    }

    /**
     * @Description 获取服务总线层的最新版本号
     * @Author sunshaoyu
     * @Date 2020/8/5-14:48
     * @param repositoryType
     * @return java.lang.String
     */
    public static String getLastestIobusVersion(String repositoryType){
        BizUtil.fieldNotNull(repositoryType, SdtDict.A.repository_type.getId(), SdtDict.A.repository_type.getLongName());
        StringBuffer buffer = new StringBuffer();
        List<SdpModuleMapping> moduleMapList = moduleMapService.queryAllModuleList();

        moduleMapList.forEach(module -> {
            String url = String.format(NEXUS_VER_FORMAT, repositoryType, module.getModuleId(), module.getModuleId(), E_BUSIMODULE.iobus);
            try {
                buffer.append(String.format("%s-%s", module.getModuleId(), E_BUSIMODULE.iobus)).append(":").append(XmlParser.searchXmlElement(XmlParser.getUrlRootElement(url), "latest").getText());
                buffer.append("\r\n");
            } catch (Exception e) {
                throw new SdtException("Failed to get the latest version number from nexus", e);
            }
        });
        return buffer.toString();
    }
}
