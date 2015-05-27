package com.zebra.data.mapper;

import java.util.LinkedHashMap;
import java.util.Map;


public class SqlInfo {

	private String sql = "";
	private Map<String, Object> params = new LinkedHashMap<String, Object>();
	
	public SqlInfo() {
		
	}
	
	public SqlInfo(String sql, Map<String, Object> params) {
		this.sql = sql;
		this.params = params;
	}
	
	public String getSql() {
		return sql;
	}
	
	public void setSql(String sql) {
		this.sql = sql;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	public void addSql(String sql) {
		this.sql = this.sql + sql;
	}
	
	public void addParams(Map<String, Object> params) {
		this.params.putAll(params);
	}
	
	public void addSqlInfo(SqlInfo sqlInfo) {
		this.sql = this.sql + sqlInfo.getSql();
		this.params.putAll(sqlInfo.getParams());
	}
}
