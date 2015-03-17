package com.github.zebbra.common.ruleengine.groovy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.zebbra.common.ruleengine.Rule;
import com.github.zebbra.common.ruleengine.RuleEngine;
import com.github.zebbra.common.ruleengine.RuleSession;

public class GroovyRuleEngine implements RuleEngine {

	public static final String ENGONE_NAME = "rule.engine.groovy";
	
	// Map<uri, RuleSet>
	private final Map<String, Rule> rules = new ConcurrentHashMap<String, Rule>();

	@Override
	public String getName() {
		return ENGONE_NAME;
	}
	
	@Override
	public RuleSession createRuleSession(Rule rule) {
		rules.put(rule.getName(), rule);
		RuleSession session = new GroovyRuleSession(rule);
		return session;
	}

}
