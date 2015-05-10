package com.zebra.utils.db.dialect.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import com.zebra.utils.CollectionUtil;
import com.zebra.utils.PageUtil;
import com.zebra.utils.db.DbUtil;
import com.zebra.utils.db.Entity;
import com.zebra.utils.db.SqlBuilder;
import com.zebra.utils.db.SqlBuilder.LogicalOperator;
import com.zebra.utils.db.SqlBuilder.Order;

/**
 * Oracle 方言
 * @author loolly
 *
 */
public class OracleDialect extends AnsiSqlDialect{
	
	@Override
	public PreparedStatement psForInsert(Connection conn, Entity entity) throws SQLException {
		final StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO `").append(entity.getTableName()).append("`(");

		final StringBuilder placeHolder = new StringBuilder();
		placeHolder.append(") values(");

		final List<Object> paramValues = new ArrayList<Object>(entity.size());
		for (Entry<String, Object> entry : entity.entrySet()) {
			if (paramValues.size() > 0) {
				sql.append(", ");
				placeHolder.append(", ");
			}
			sql.append("`").append(entry.getKey()).append("`");
			final Object value = entry.getValue();
			if(value instanceof String && ((String)value).endsWith(".nextval")) {
				//Oracle的特殊自增键，通过字段名.nextval获得下一个值
				placeHolder.append(value);
			}else {
				placeHolder.append("?");
				paramValues.add(value);
			}
		}
		sql.append(placeHolder.toString()).append(")");

		final PreparedStatement ps = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		DbUtil.fillParams(ps, paramValues.toArray(new Object[paramValues.size()]));
		return ps;
	}
	
	@Override
	public PreparedStatement psForPage(Connection conn, Collection<String> fields, Entity where, int page, int numPerPage) throws SQLException {
		return psForPage(conn, fields, where, page, numPerPage, null, null);
	}
	
	@Override
	public PreparedStatement psForPage(Connection conn, Collection<String> fields, Entity where, int page, int numPerPage, Collection<String> orderFields, Order order) throws SQLException {
		final SqlBuilder find = SqlBuilder.create(wrapper)
				.select(fields)
				.from(where.getTableName())
				.where(LogicalOperator.AND, DbUtil.buildConditions(where));
		
		if(null != order && CollectionUtil.isNotEmpty(orderFields)) {
			find.orderBy(order, orderFields.toArray(new String[orderFields.size()]));
		}
		
		int[] startEnd = PageUtil.transToStartEnd(page, numPerPage);
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ( SELECT row_.*, rownum rownum_ from ( ")
			.append(find)
			.append(" ) row_ where rownum <= ").append(startEnd[1])
			.append(") table_alias")
			.append(" where table_alias.rownum_ >= ").append(startEnd[0]);
		
		final PreparedStatement ps = conn.prepareStatement(sql.toString());
		DbUtil.fillParams(ps, find.getParamValueArray());
		return ps;
	}
}
