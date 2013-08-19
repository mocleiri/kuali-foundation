package org.kuali.common.util.log.log4j.model;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.Assert;

public final class Appender {

	public static final List<Param> DEFAULT_PARAMS = Collections.<Param> emptyList();

	@XmlAttribute
	private final String name;

	@XmlAttribute(name = "class")
	private final Class<?> javaClass;
	
	private final Layout layout;

	@XmlElement(name = "param")
	private final List<Param> params;

	public Appender(String name, Class<?> javaClass, Layout layout) {
		this(name, javaClass, layout, DEFAULT_PARAMS);
	}

	public Appender(String name, Class<?> javaClass, Layout layout, List<Param> params) {
		Assert.noBlanks(name);
		Assert.noNulls(javaClass, layout, params);
		this.name = name;
		this.javaClass = javaClass;
		this.layout = layout;
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public Class<?> getJavaClass() {
		return javaClass;
	}

	public List<Param> getParams() {
		return params;
	}

	public Layout getLayout() {
		return layout;
	}

}
