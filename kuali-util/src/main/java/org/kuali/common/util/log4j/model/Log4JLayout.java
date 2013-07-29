package org.kuali.common.util.log4j.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Log4JLayout {

	Class<?> javaClass;
	List<Log4JParam> params;

	@XmlAttribute(name = "class")
	public Class<?> getJavaClass() {
		return javaClass;
	}

	@XmlElement(name = "param")
	public List<Log4JParam> getParams() {
		return params;
	}

	public void setJavaClass(Class<?> javaClass) {
		this.javaClass = javaClass;
	}

	public void setParams(List<Log4JParam> params) {
		this.params = params;
	}

}
