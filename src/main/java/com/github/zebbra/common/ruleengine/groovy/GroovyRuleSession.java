package com.github.zebbra.common.ruleengine.groovy;

import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import com.github.zebbra.common.ruleengine.Rule;
import com.github.zebbra.common.ruleengine.RuleEngineException;
import com.github.zebbra.common.ruleengine.RuleSession;

public class GroovyRuleSession implements RuleSession{
	
	private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("groovy");
	
	private final Map<String, Object> workingMemory = new HashMap<String, Object>();

	private Rule rule;
	
	public GroovyRuleSession(Rule rule) {
		if (rule == null) {
			throw new IllegalArgumentException("rule is null");
		}
		this.rule= rule;
	}
	
	@Override
	public void executeRule() {
		Bindings bindings = new SimpleBindings();
		bindings.putAll(workingMemory);
		try {
			engine.eval(rule.getExpression(), bindings);
		} catch (ScriptException e) {
			throw new RuleEngineException("Groovy script excution error, \nrule expression:\n"+rule.getExpression()+"\ninput data:\n"+ new HashMap<String, Object>(bindings), e);
		}
		workingMemory.putAll(bindings);
	}
	
	@Override
	public void reset() {
		workingMemory.clear();
	}

	@Override
	public void addDataSet(Map<String, Object> dataSet) {
		workingMemory.putAll(dataSet);
	}

	@Override
	public void addDataSet(String key, Object value) {
		workingMemory.put(key, value);
		
	}
	
	@Override
	public Map<String, Object> getDataSet() {
		return workingMemory;
	}

	@Override
	public void setRule(Rule rule) {
		this.rule = rule;
		
	}

	@Override
	public Rule getRule() {
		return rule;
	}

}
