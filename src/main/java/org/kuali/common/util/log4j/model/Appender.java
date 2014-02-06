package org.kuali.common.util.log4j.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @deprecated
 */
@Deprecated
public class Appender {

	String name;
	Class<?> javaClass;
	Layout layout;
	List<Param> params = new ArrayList<Param>();

	public Appender(Appender appender) {
		super();
		this.name = appender.getName();
		this.javaClass = appender.getJavaClass();
		this.layout = appender.getLayout();
		for (Param param : params) {
			this.params.add(new Param(param));
		}
	}

	public Appender() {
		this(null, null, null);
	}

	public Appender(String name, Class<?> javaClass, Layout layout) {
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

	@XmlElement(name = "param")
	public List<Param> getParams() {
		return params;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setJavaClass(Class<?> javaClass) {
		this.javaClass = javaClass;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}
}
