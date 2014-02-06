package org.kuali.common.util.main;

import org.kuali.common.util.Assert;

public final class MainContext {

	public MainContext(Class<?> mainClass, String[] args) {
		Assert.noNulls(mainClass);
		this.mainClass = mainClass;
		this.args = args;
	}

	private final Class<?> mainClass;
	private final String[] args;

	public Class<?> getMainClass() {
		return mainClass;
	}

	public String[] getArgs() {
		return args;
	}

}
