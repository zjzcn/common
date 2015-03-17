package com.github.zebbra.common.ruleengine;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Rule implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String name;

	private String expression;
	
	private String description;

	private final Map<String, Object> attributes = new HashMap<String, Object>();

	public Rule() {		
	}
	
	public Rule(String name, String expression) {
		this.name = name;
		this.expression = expression;
	}
	
	public Rule(String name, String expression, String description) {
		this.name = name;
		this.expression = expression;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
}
