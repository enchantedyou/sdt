package com.ssy.api.entity.dict;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 项目字典
 * @Author sunshaoyu
 * @Date 2020年06月12日-16:28
 */
public class SdtDict {

    public static enum A implements DefaultEnum<String> {

        dict_type("dictType", "dict type", "字典类型", 16, 0),
        dict_priority("dictPriority", "dict priority", "字典优先级", 3, 0),
        enum_type("enumType", "enum type", "枚举类型", 16, 0),
        enum_priority("enumPriority", "enum priority", "枚举优先级", 3, 0),
        is_enabled("isEnabled", "is enabled", "是否优先", 1, 0),
        group_id("groupId", "group id", "组号", 2, 0),
        trxn_date("trxnDate", "transaction date", "交易日期", 8, 0),
        data_create_user("dataCreateUser", "data create user", "数据创建者", 32, 0),
        data_create_time("dataCreateTime", "data create time", "数据创建时间", 32, 0),
        data_update_user("dataUpdateUser", "data update user", "数据更新者", 32, 0),
        data_update_time("dataUpdateTime", "data update time", "数据更新时间", 32, 0),
        data_version("dataVersion", "data version", "数据版本号", 19, 0),
        ;

        private String id;
        private String longname;
        private String description;
        private int length;
        private int fractionDigits;

        private A(String id, String longname, String description, int length, int fractionDigits) {
            this.id = id;
            this.longname = longname;
            this.description = description;
            this.length = length;
            this.fractionDigits = fractionDigits;
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
        public String getLongName() {
            return this.longname;
        }

        public String getDescription() {
            return this.description;
        }

        public int getLength() {
            return this.length;
        }

        public int getFractionDigits() {
            return this.fractionDigits;
        }
    }
}
