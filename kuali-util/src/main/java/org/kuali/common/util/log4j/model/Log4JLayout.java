package org.kuali.common.util.log4j.model;

import java.util.List;

public class Log4JLayout {

	Class<?> javaClass;
	List<Log4JParam> params;

	public Class<?> getJavaClass() {
		return javaClass;
	}

	public void setJavaClass(Class<?> javaClass) {
		this.javaClass = javaClass;
	}

	public List<Log4JParam> getParams() {
		return params;
	}

	public void setParams(List<Log4JParam> params) {
		this.params = params;
	}

}
