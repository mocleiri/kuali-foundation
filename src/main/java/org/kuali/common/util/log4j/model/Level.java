package org.kuali.common.util.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @deprecated
 */
@Deprecated
public class Level {

	public static final Class<org.apache.log4j.Level> DEFAULT_JAVA_CLASS = org.apache.log4j.Level.class;

	Class<?> javaClass = DEFAULT_JAVA_CLASS;
	Value value;

	public Level(Level level) {
		super();
		this.javaClass = level.getJavaClass();
		this.value = level.getValue();
	}

	public Level() {
		this((Value) null);
	}

	public Level(Value value) {
		super();
		this.value = value;
	}

	@XmlAttribute(name = "class")
	public Class<?> getJavaClass() {
		return javaClass;
	}

	@XmlAttribute
	public Value getValue() {
		return value;
	}

	public void setJavaClass(Class<?> javaClass) {
		this.javaClass = javaClass;
	}

	public void setValue(Value value) {
		this.value = value;
	}

}
