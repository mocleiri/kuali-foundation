package org.kuali.common.util.spring.main;

import org.kuali.common.util.Assert;

public final class MainContext {

	public MainContext(Class<?> javaClass, String[] args) {
		Assert.noNulls(javaClass, args);
		this.javaClass = javaClass;
		this.args = args;
	}

	private final Class<?> javaClass;
	private final String[] args;

	public Class<?> getJavaClass() {
		return javaClass;
	}

	public String[] getArgs() {
		return args;
	}

}
