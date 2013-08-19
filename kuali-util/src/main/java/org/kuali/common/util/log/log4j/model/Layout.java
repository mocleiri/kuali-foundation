package org.kuali.common.util.log.log4j.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.nullify.Null;

public final class Layout {

	public static final Class<?> NO_CLASS = Null.class;
	public static final List<Param> NO_PARAMS = Collections.<Param> emptyList();
	public static final Layout NO_LAYOUT = new Layout(NO_CLASS, NO_PARAMS);

	@XmlAttribute(name = "class")
	private final Class<?> layoutClass;

	@XmlElement(name = "param")
	private final List<Param> params;

	@SuppressWarnings("unused")
	private Layout() {
		this(NO_CLASS, new ArrayList<Param>());
	}

	public Layout(Class<?> layoutClass, Param param) {
		this(layoutClass, CollectionUtils.singletonList(param));
	}

	public Layout(Class<?> layoutClass, List<Param> params) {
		Assert.noNulls(layoutClass, params);
		this.layoutClass = layoutClass;
		this.params = params;
	}

	public Class<?> getLayoutClass() {
		return layoutClass;
	}

	public List<Param> getParams() {
		return Collections.unmodifiableList(params);
	}

}
