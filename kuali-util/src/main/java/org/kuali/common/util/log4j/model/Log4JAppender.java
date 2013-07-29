package org.kuali.common.util.log4j.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

public class Log4JAppender {

	String name;
	Class<?> javaClass;
	Log4JLayout layout;
	List<Log4JParam> params;

	public Log4JAppender() {
		this(null, null, null);
	}

	public Log4JAppender(String name, Class<?> javaClass, Log4JLayout layout) {
		super();
		this.name = name;
		this.javaClass = javaClass;
		this.layout = layout;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	@XmlAttribute(name = "class")
	public Class<?> getJavaClass() {
		return javaClass;
	}

	public void setName(String name) {
		this.name = name;
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
