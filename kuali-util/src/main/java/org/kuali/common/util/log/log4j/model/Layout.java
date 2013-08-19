package org.kuali.common.util.log.log4j.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;

public final class Layout {

	private final Class<?> javaClass;
	private final List<Param> params;

	public Layout(Class<?> javaClass, Param param) {
		this(javaClass, CollectionUtils.singletonList(param));
	}

	public Layout(Class<?> javaClass, List<Param> params) {
		Assert.noNulls(javaClass, params);
		this.javaClass = javaClass;
		this.params = params;
	}

	@XmlAttribute(name = "class")
	public Class<?> getJavaClass() {
		return javaClass;
	}

	@XmlElement(name = "param")
	public List<Param> getParams() {
		return params;
	}

}
