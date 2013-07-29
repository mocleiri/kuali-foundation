package org.kuali.common.util.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

import org.apache.log4j.Level;

public class Log4JLevel {

	public static final Class<Level> DEFAULT_JAVA_CLASS = Level.class;

	public Log4JLevel() {
		this(null);
	}

	public Log4JLevel(String value) {
		super();
		this.value = value;
	}

	Class<?> javaClass = DEFAULT_JAVA_CLASS;
	String value;

	@XmlAttribute(name = "class")
	public Class<?> getJavaClass() {
		return javaClass;
	}

	@XmlAttribute
	public String getValue() {
		return value;
	}

	public void setJavaClass(Class<?> javaClass) {
		this.javaClass = javaClass;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
