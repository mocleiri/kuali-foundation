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

	public static final Layout NONE = new Layout();

	@XmlAttribute(name = "class")
	private final Class<?> layoutClass;

	@XmlElement(name = "param")
	private final List<Param> params;

	private Layout() {
		this(Null.class, Param.EMPTY);
	}

	public Layout(Class<?> layoutClass, Param param) {
		this(layoutClass, CollectionUtils.singletonList(param));
	}

	public Layout(Class<?> layoutClass, List<Param> params) {
		Assert.noNulls(layoutClass, params);
		this.layoutClass = layoutClass;
		this.params = new ArrayList<Param>(params);
	}

	public List<Param> getParams() {
		return Collections.unmodifiableList(params);
	}

	public Class<?> getLayoutClass() {
		return layoutClass;
	}

}
