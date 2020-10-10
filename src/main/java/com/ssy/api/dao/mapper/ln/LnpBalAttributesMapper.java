package com.ssy.api.dao.mapper.ln;

import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.ln.LnpBalAttributes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@TableType(name = "lnp_bal_attributes", desc = "loan balance attributes parameter")
public interface LnpBalAttributesMapper {
    int deleteByPrimaryKey(@Param("balAttributes") String balAttributes, @Param("orgId") String orgId);

    int insert(LnpBalAttributes record);

    int insertSelective(LnpBalAttributes record);

    LnpBalAttributes selectByPrimaryKey(@Param("balAttributes") String balAttributes, @Param("orgId") String orgId);

    int updateByPrimaryKeySelective(LnpBalAttributes record);

    int updateByPrimaryKey(LnpBalAttributes record);

    /** 根据法人代码查属性列表 **/
    @EnableNotNull
    List<LnpBalAttributes> selectAll_odb1(String orgId, boolean nullException);
}