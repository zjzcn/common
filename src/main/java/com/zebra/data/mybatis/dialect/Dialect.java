package com.zebra.data.mybatis.dialect;

public interface Dialect {
	
	static final String MYSQL = "mysql";
	static final String ORACLE = "oracle";
	
	String getPageSql(String sql, int offset, int limit);
	
	String getCountSql(String sql);
}
