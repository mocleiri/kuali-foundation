package org.kuali.common.util.log.log4j.model;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.log.log4j.NoOpAppender;
import org.kuali.common.util.nullify.NullUtils;

public final class Appender {

	public static final List<Param> DEFAULT_PARAMS = Collections.<Param> emptyList();
	public static final String DEFAULT_NAME = NullUtils.NONE;
	public static final Class<NoOpAppender> DEFAULT_APPENDER_CLASS = NoOpAppender.class;

	@XmlAttribute
	private final String name;

	@XmlAttribute(name = "class")
	private final Class<? extends Appender> appenderClass;

	private final Layout layout;

	@XmlElement(name = "param")
	private final List<Param> params;

	public Appender(String name, Class<? extends Appender> appenderClass, Layout layout) {
		this(name, appenderClass, layout, DEFAULT_PARAMS);
	}

	public Appender(String name, Class<? extends Appender> appenderClass, Layout layout, List<Param> params) {
		Assert.noBlanks(name);
		Assert.noNulls(appenderClass, layout, params);
		this.name = name;
		this.appenderClass = appenderClass;
		this.layout = layout;
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public Class<? extends Appender> getAppenderClass() {
		return appenderClass;
	}

	public List<Param> getParams() {
		return params;
	}

	public Layout getLayout() {
		return layout;
	}

}
