package org.kuali.common.util.log4j;

import java.util.Map;

public class Log4JAppender {

	String name;
	Class<?> javaClass;
	Log4JLayout layout;
	Map<String, String> params;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getJavaClass() {
		return javaClass;
	}

	public void setJavaClass(Class<?> javaClass) {
		this.javaClass = javaClass;
	}

	public Log4JLayout getLayout() {
		return layout;
	}

	public void setLayout(Log4JLayout layout) {
		this.layout = layout;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

}
