package org.kuali.common.util.log4j.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.CollectionUtils;

/**
 * @deprecated
 */
@Deprecated
public class Layout {

	Class<?> javaClass;
	List<Param> params = new ArrayList<Param>();

	public Layout(Layout layout) {
		super();
		this.javaClass = layout.getJavaClass();
		for (Param param : CollectionUtils.toEmptyList(layout.getParams())) {
			this.params.add(new Param(param));
		}
	}

	public Layout() {
		this(null, (List<Param>) null);
	}

	public Layout(Class<?> javaClass, Param param) {
		this(javaClass, Arrays.asList(param));
	}

	public Layout(Class<?> javaClass, List<Param> params) {
		super();
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

	public void setJavaClass(Class<?> javaClass) {
		this.javaClass = javaClass;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}

}
