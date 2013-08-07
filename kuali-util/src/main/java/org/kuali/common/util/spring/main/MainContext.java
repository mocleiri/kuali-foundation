package org.kuali.common.util.spring.main;

public final class MainContext {

	public MainContext(Class<?> getJavaClass, String[] args) {
		super();
		this.javaClass = getJavaClass;
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
