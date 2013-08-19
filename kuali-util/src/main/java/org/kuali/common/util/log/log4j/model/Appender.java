package org.kuali.common.util.log.log4j.model;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;
import org.springframework.util.CollectionUtils;

public final class Appender {

	public static final List<Param> NO_PARAMS = Collections.<Param> emptyList();
	public static final String NO_NAME = NullUtils.NONE;
	public static final Class<? extends Appender> NO_APPENDER_CLASS = Appender.class;
	public static final Appender NO_APPENDER = new Appender();

	@XmlAttribute
	private final String name;

	@XmlAttribute(name = "class")
	private final Class<? extends Appender> appenderClass;

	@XmlElement(name = "param")
	private final List<Param> params;

	private final Layout layout;

	private Appender() {
		this(NO_NAME, NO_APPENDER_CLASS, Layout.NO_LAYOUT);
	}

	public Appender(String name, Class<? extends Appender> appenderClass, Layout layout) {
		this(name, appenderClass, layout, NO_PARAMS);
	}

	public Appender(String name, Class<? extends Appender> appenderClass, Layout layout, List<Param> params) {
		Assert.noBlanks(name);
		Assert.noNulls(appenderClass, layout, params);
		this.name = name;
		this.appenderClass = appenderClass;
		this.layout = layout;
		this.params = CollectionUtils.isEmpty(params) ? NO_PARAMS : params;
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
