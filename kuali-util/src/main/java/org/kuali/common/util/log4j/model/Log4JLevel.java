package org.kuali.common.util.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

import org.apache.log4j.Level;

public class Log4JLevel {

	public static final Class<Level> DEFAULT_JAVA_CLASS = Level.class;

	Class<?> javaClass = DEFAULT_JAVA_CLASS;
	Level value;

	@XmlAttribute
	public Class<?> getJavaClass() {
		return javaClass;
	}

	@XmlAttribute
	public Level getValue() {
		return value;
	}

	public void setJavaClass(Class<?> javaClass) {
		this.javaClass = javaClass;
	}

	public void setValue(Level value) {
		this.value = value;
	}

}
