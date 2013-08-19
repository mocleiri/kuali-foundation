package org.kuali.common.util.log.log4j.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.nullify.Null;

public final class Layout {

	public static final Class<?> DEFAULT_JAVA_CLASS = Null.class;
	public static final List<Param> DEFAULT_PARAMS = Collections.<Param> emptyList();

	@XmlAttribute(name = "class")
	private final Class<?> javaClass;

	@XmlAttribute(name = "class")
	private final List<Param> params;

	@SuppressWarnings("unused")
	private Layout() {
		this(DEFAULT_JAVA_CLASS, new ArrayList<Param>());
	}

	public Layout(Class<?> javaClass, Param param) {
		this(javaClass, CollectionUtils.singletonList(param));
	}

	public Layout(Class<?> javaClass, List<Param> params) {
		Assert.noNulls(javaClass, params);
		this.javaClass = javaClass;
		this.params = params;
	}

	public Class<?> getJavaClass() {
		return javaClass;
	}

	public List<Param> getParams() {
		return Collections.unmodifiableList(params);
	}

}
