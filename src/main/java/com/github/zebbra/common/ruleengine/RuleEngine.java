package com.github.zebbra.common.ruleengine;


public interface RuleEngine {
	
	String getName();
	
	RuleSession createRuleSession(Rule rule);
	
}
