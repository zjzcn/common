package com.zebra.utils.db.dialect.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import com.zebra.utils.CollectionUtil;
import com.zebra.utils.PageUtil;
import com.zebra.utils.db.DbUtil;
import com.zebra.utils.db.Entity;
import com.zebra.utils.db.SqlBuilder;
import com.zebra.utils.db.Wrapper;
import com.zebra.utils.db.SqlBuilder.LogicalOperator;
import com.zebra.utils.db.SqlBuilder.Order;


/**
 * SqlLite3方言
 * @author loolly
 *
 */
public class PostgresqlDialect extends AnsiSqlDialect{
	public PostgresqlDialect() {
		wrapper = new Wrapper('"');
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
		find.append(" limit ").append(startEnd[0]).append(" offset ").append(numPerPage);
		
		final PreparedStatement ps = conn.prepareStatement(find.build());
		DbUtil.fillParams(ps, find.getParamValueArray());
		return ps;
	}
}
