package org.kuali.common.util.log4j.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.CollectionUtils;

public class Log4JLayout {

	Class<?> javaClass;
	List<Log4JParam> params = new ArrayList<Log4JParam>();

	public Log4JLayout(Log4JLayout layout) {
		super();
		this.javaClass = layout.getJavaClass();
		for (Log4JParam param : CollectionUtils.toEmptyList(layout.getParams())) {
			params.add(new Log4JParam(param));
		}
	}

	public Log4JLayout() {
		this(null, null);
	}

	public Log4JLayout(Class<?> javaClass, List<Log4JParam> params) {
		super();
		this.javaClass = javaClass;
		this.params = params;
	}

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
