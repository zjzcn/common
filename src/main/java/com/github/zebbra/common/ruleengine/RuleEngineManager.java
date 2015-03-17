package com.github.zebbra.common.ruleengine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

import com.github.zebbra.common.ruleengine.groovy.GroovyRuleEngine;

public final class RuleEngineManager {
	
	private static final Map<String, RuleEngine> engines = new HashMap<String, RuleEngine>();
	
	static {
		initEngine();
	}
	
	public static RuleEngine getRuleEngine(String name) {
		RuleEngine engine = engines.get(name);
		if(engine == null) {
			throw new RuleEngineException("Can not get rule engine of name["+name+"].");
		}
		return engine;
	}
	
	public static void registerRuleEngine(String name, RuleEngine ruleEngine) {
		engines.put(name, ruleEngine);
	}

	private static void initEngine(){
		ServiceLoader<RuleEngine> loader;
		loader = ServiceLoader.load(RuleEngine.class);
		Iterator<RuleEngine> itr = loader.iterator();
		while (itr.hasNext()) {
			RuleEngine engine = itr.next();
			registerRuleEngine(engine.getName(), engine);
		}
	}
	
	public static void main(String[] args) {
		RuleEngine engine= RuleEngineManager.getRuleEngine(GroovyRuleEngine.ENGONE_NAME);
		
		String rule = "println \"groory:${name}\"+\"kkkk\";name=name+'eeee'; name1=\"hehehe\";";
		Rule r = new Rule("hehe", rule);
		RuleSession session = engine.createRuleSession(r);
		session.addDataSet("name", "dev hehe");
		session.executeRule();
		Map<String, Object> result = session.getDataSet();
		
		System.out.println(result);
	}
}
