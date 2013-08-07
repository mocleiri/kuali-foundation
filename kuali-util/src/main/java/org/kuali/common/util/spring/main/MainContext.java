package org.kuali.common.util.spring.main;

public class MainContext {

	Class<?> mainClass;
	String[] args;

	public Class<?> getMainClass() {
		return mainClass;
	}

	public void setMainClass(Class<?> mainClass) {
		this.mainClass = mainClass;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

}
