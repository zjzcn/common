package com.zebra.data.mapper.model;


public class SqlInfo {

	private String sql;
	private Object[] params;
	private int pageNo = -1;
	private int pageSize = -1;
	
	public SqlInfo() {
		
	}
	
	public SqlInfo(String sql, Object... params) {
		this.sql = sql;
		this.params = params;
	}
	
	public SqlInfo(int pageNo, int pageSize, String sql, Object... params) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.sql = sql;
		this.params = params;
	}
	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
