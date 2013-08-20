package org.kuali.common.util.log.log4j.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.log.log4j.jaxb.ParamListAdapter;
import org.kuali.common.util.nullify.NullUtils;

public final class Appender {

	public static final String NO_NAME = NullUtils.NONE;
	public static final Class<? extends org.apache.log4j.Appender> NO_APPENDER_CLASS = org.apache.log4j.Appender.class;
	public static final Appender NO_APPENDER = new Appender();

	@XmlAttribute
	private final String name;

	@XmlAttribute(name = "class")
	private final Class<? extends org.apache.log4j.Appender> appenderClass;

	@XmlElement(name = "param")
	@XmlJavaTypeAdapter(ParamListAdapter.class)
	private final List<Param> params;

	@XmlElement
	private final Layout layout;

	private Appender() {
		this(NO_NAME, NO_APPENDER_CLASS, Layout.NO_LAYOUT, new ArrayList<Param>());
	}

	public Appender(String name, Class<? extends org.apache.log4j.Appender> appenderClass, Layout layout) {
		this(name, appenderClass, layout, Param.NO_PARAMS);
	}

	public Appender(String name, Class<? extends org.apache.log4j.Appender> appenderClass, Layout layout, List<Param> params) {
		Assert.noBlanks(name);
		Assert.noNulls(appenderClass, layout, params);
		this.name = name;
		this.appenderClass = appenderClass;
		this.layout = layout;
		this.params = CollectionUtils.unmodifiableCopy(params);
	}

	public String getName() {
		return name;
	}

	public Class<? extends org.apache.log4j.Appender> getAppenderClass() {
		return appenderClass;
	}

	public List<Param> getParams() {
		return params;
	}

	public Layout getLayout() {
		return layout;
	}

}
