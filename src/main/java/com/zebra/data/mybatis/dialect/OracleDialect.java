package com.zebra.data.mybatis.dialect;

public class OracleDialect implements Dialect {

	@Override
	public String getPageSql(String sql, int offset, int limit) {
		StringBuilder sqlBuilder = new StringBuilder(sql);
		
		sqlBuilder.append("select * from ( select temp.*, rownum row_id from ( ");
		sqlBuilder.append(sql);
		sqlBuilder.append(" ) temp where rownum <= ").append(offset+limit);
		sqlBuilder.append(") where row_id > ").append(offset);
		
		return sqlBuilder.toString();
	}

	@Override
	public String getCountSql(String sql) {
		StringBuilder sqlBuilder = new StringBuilder();
		
		return sqlBuilder.append("select count(*) from (").
				append(sql).append(") tmp_count").toString();
	}

}
