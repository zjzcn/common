package com.zebra.data.mapper;

import java.util.Map;


public class MapperParam {

	private Condition condition;
	
	private Class<?> entityClass;
	private Object id;
	
	private String sql;
	private Map<String, Object> params;
	
	public MapperParam(Condition condition) {
		this.condition = condition;
	}
	
	public MapperParam(Class<?> entityClass, Object id) {
		this.entityClass = entityClass;
		this.id = id;
	}
	
	public MapperParam(String sql, Map<String, Object> params) {
		this.sql = sql;
		this.params = params;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
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
	
}
