package com.github.zebbra.common.ruleengine;

import java.util.Map;

public interface RuleSession {
	
	void addDataSet(Map<String, Object> dateSet);
	
	void addDataSet(String key, Object value);
	
	Map<String, Object> getDataSet();
	
	void executeRule();
	
	void setRule(Rule rule);
	
	Rule getRule();
	
	void reset();
}
