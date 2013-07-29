package org.kuali.common.util.log4j.model;

import java.util.List;

public class Log4JAppender {

	String name;
	Class<?> javaClass;
	Log4JLayout layout;
	List<Log4JParam> params;

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

	public List<Log4JParam> getParams() {
		return params;
	}

	public void setParams(List<Log4JParam> params) {
		this.params = params;
	}
}
