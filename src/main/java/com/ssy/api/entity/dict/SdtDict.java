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
        flow_group("flowGroup", "batch flow group", "批量流程组", 20, 0),
        batch_call_mode("batchCallMode", "batch call mode", "批量唤醒模式", 4, 0),
        end_date("endDate", "end date", "结束日期", 8, 0),
        assign_days("assignDays", "assign days", "指定天数", 19, 0),
        datasource_id("datasourceId", "datasource identity", "数据源编号", 50, 0),
        user_acct("userAcct", "user account", "用户账号", 50, 0),
        user_pwd("userPwd", "user password", "用户密码", 50, 0),
        key("key", "key", "关键字", 100, 0),
        pte_module("pteModule", "PTE module", "PTE模板", 100, 0),
        flowtran_id("flowtranId", "flowtran id", "交易流程编号", 6, 0),
        original_flowtran_id("originalFlowtranId", "original flowtran id", "原交易流程编号", 6, 0),
        list_name("listName", "list name", "列表名称", 50, 0),
        merge_url("mergeUrl", "merge url", "合并链接", 250, 0),
        module_id("moduleId", "module identity", "模块编号", 2, 0),
        inner_service_code("innerServiceCode", "inner service code", "内部服务码", 6, 0),
        service_code("servicecode", "service code", "服务码", 6, 0),
        jira_id("jiraId", "jira identity", "JIRA编号", 15, 0),
        repository_type("repositoryType", "repository type", "仓库类型", 20, 0),
        data_operate_type("dataOperateType", "data operate type", "数据源操作类型", 1, 0),
        complex_location("complexLocation", "complex location", "复合类型位置", 50, 0),
        service_location("serviceLocation", "service location", "服务类型位置", 50, 0),
        source_entity_id("sourceEntityId", "source entity identity", "源实体类型编号", 50, 0),
        source_var_name("sourceVarName", "source variable name", "源变量名", 30, 0),
        target_entity_id("targetEntityId", "target entity identity", "目标实体类型编号", 50, 0),
        target_var_name("targetVarName", "target variable name", "目标变量名", 30, 0),
        jira_id_length("jiraIdLength", "jira identity length", "JIRA编号长度", 15, 0)
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
