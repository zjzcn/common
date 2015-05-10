/*
 * Copyright (C), 2014-2014, 联创车盟汽车服务有限公司
 * FileName: MyEnumTypeHandler.java
 * Author:   ZGF
 * Date:     2014年6月18日 下午6:39:30
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.zebra.data;

import ocean.core.type.EnumCodeGetter;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author ZGF
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public abstract class EnumCodeTypeHandler<E extends Enum<E> & EnumCodeGetter> extends BaseTypeHandler<E> {

    private final E[] enums;

    public EnumCodeTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String name) throws SQLException {
        return convert(rs.getString(name));
    }

    @Override
    public E getNullableResult(ResultSet rs, int i) throws SQLException {
        return convert(rs.getString(i));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int i) throws SQLException {
        return convert(cs.getString(i));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E enumObj, JdbcType type) throws SQLException {
        ps.setString(i, enumObj.getCode());
    }

    private E convert(String status) {
        for (E em : enums) {
            if (em.getCode().equals(status)) {
                return em;
            }
        }
        return null;
    }
}
