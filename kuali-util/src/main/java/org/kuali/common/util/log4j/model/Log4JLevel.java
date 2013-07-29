package org.kuali.common.util.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

import org.apache.log4j.Level;

public class Log4JLevel {

	public static final Class<Level> DEFAULT_JAVA_CLASS = Level.class;

	public Log4JLevel(Log4JLevel level) {
		super();
		this.javaClass = level.getJavaClass();
		this.value = level.getValue();
	}

	public Log4JLevel() {
		this((Log4JLevelValue) null);
	}

	public Log4JLevel(Log4JLevelValue value) {
		super();
		this.value = value;
	}

	Class<?> javaClass = DEFAULT_JAVA_CLASS;
	Log4JLevelValue value;

	@XmlAttribute(name = "class")
	public Class<?> getJavaClass() {
		return javaClass;
	}

	@XmlAttribute
	public Log4JLevelValue getValue() {
		return value;
	}

	public void setJavaClass(Class<?> javaClass) {
		this.javaClass = javaClass;
	}

	public void setValue(Log4JLevelValue value) {
		this.value = value;
	}

}
