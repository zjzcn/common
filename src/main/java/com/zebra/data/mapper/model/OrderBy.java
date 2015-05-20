package com.zebra.data.mapper.model;

public class OrderBy {

	private Op op;
	private String propertyName;

	public OrderBy(Op op, String propertyName) {
		this.op = op;
		this.propertyName = propertyName;
	}
	
	public Op getOp() {
		return op;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public enum Op {
		ASC, DESC
	}
}
