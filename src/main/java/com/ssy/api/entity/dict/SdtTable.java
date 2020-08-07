package com.ssy.api.entity.dict;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 项目表
 * @Author sunshaoyu
 * @Date 2020年06月12日-17:09
 */
public class SdtTable {

    public static enum A implements DefaultEnum<String> {

        sdp_dict_priority("SdpDictPriority", "dict priority", "字典优先级"),
        sdp_enum_priority("SdpEnumPriority", "enum priority", "枚举优先级"),
        msp_organization("MspOrganization", "business organization indicator", "组织机构"),
        app_date("AppDate", "transaction date", "交易日期"),
        sdp_datasource("SdpDatasource", "data source", "数据源"),
        ;

        private String id;
        private String longname;
        private String description;

        private A(String id, String longname, String description) {
            this.id = id;
            this.longname = longname;
            this.description = description;
        }

        @Override
        public String getId() {
            return this.id;
        }

        @Override
        public String getValue() {
            return this.id;
        }

        @Override
        public final String getLongName() {
            return this.longname;
        }

        public String getDescription() {
            return this.description;
        }
    }
}
