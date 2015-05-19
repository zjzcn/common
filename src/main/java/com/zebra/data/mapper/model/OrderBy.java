package com.zebra.data.mapper.model;



public class OrderBy implements SqlBuilder {

	private Op op;
	private String propertyName;

	public OrderBy(Op op, String propertyName) {
		this.op = op;
		this.propertyName = propertyName;
	}
	
	@Override
	public SqlInfo buildSql() {
		// TODO Auto-generated method stub
		return null;
	}

	public enum Op {
		ASC, DESC
	}
}
