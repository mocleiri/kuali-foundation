package org.kuali.common.util.log.log4j.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

public final class Appender {

	public static final List<Appender> EMPTY = Collections.<Appender> emptyList();
	public static final Class<? extends org.apache.log4j.Appender> NO_APPENDER_CLASS = org.apache.log4j.Appender.class;
	public static final Appender NONE = new Appender();

	@XmlAttribute
	private final String name;

	@XmlAttribute(name = "class")
	private final Class<? extends org.apache.log4j.Appender> appenderClass;

	@XmlElement(name = "param")
	private final List<Param> params;

	@XmlElement
	private final Layout layout;

	private Appender() {
		this(NullUtils.NONE, NO_APPENDER_CLASS, Layout.NONE, Param.EMPTY);
	}

	public Appender(String name, Class<? extends org.apache.log4j.Appender> appenderClass, Layout layout) {
		this(name, appenderClass, layout, Param.EMPTY);
	}

	public Appender(String name, Class<? extends org.apache.log4j.Appender> appenderClass, Layout layout, List<Param> params) {
		Assert.noNulls(appenderClass, layout, params);
		Assert.noBlanks(name);
		this.name = name;
		this.appenderClass = appenderClass;
		this.layout = layout;
		this.params = new ArrayList<Param>(params);
	}

	public List<Param> getParams() {
		return Collections.unmodifiableList(params);
	}

	public String getName() {
		return name;
	}

	public Class<? extends org.apache.log4j.Appender> getAppenderClass() {
		return appenderClass;
	}

	public Layout getLayout() {
		return layout;
	}

}
