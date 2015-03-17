package com.github.zebbra.common.ruleengine;

public class RuleEngineException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RuleEngineException(String s) {
		super(s);
	}

	public RuleEngineException(String s, Throwable e) {
		super(s, e);
	}

	public RuleEngineException(Throwable e) {
		super(e);
	}

}