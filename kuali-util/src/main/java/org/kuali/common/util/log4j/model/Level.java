package org.kuali.common.util.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Level {

	public static final Class<org.apache.log4j.Level> DEFAULT_JAVA_CLASS = org.apache.log4j.Level.class;

	Class<?> javaClass = DEFAULT_JAVA_CLASS;
	LevelValue value;

	public Level(Level level) {
		super();
		this.javaClass = level.getJavaClass();
		this.value = level.getValue();
	}

	public Level() {
		this((LevelValue) null);
	}

	public Level(LevelValue value) {
		super();
		this.value = value;
	}

	@XmlAttribute(name = "class")
	public Class<?> getJavaClass() {
		return javaClass;
	}

	@XmlAttribute
	public LevelValue getValue() {
		return value;
	}

	public void setJavaClass(Class<?> javaClass) {
		this.javaClass = javaClass;
	}

	public void setValue(LevelValue value) {
		this.value = value;
	}

}
