package org.kuali.common.impex.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class View implements NamedElement {

	String name;
	String queryString;

	/**
	 * This is a copy constructor. It must create a perfect, deep, copy of this object
	 */
	public View(View view) {
		this.name = view.getName();
		this.queryString = view.getQueryString();
	}

	public View() {
		this(null, null);
	}

	public View(String name, String queryString) {
		this.name = name;
		this.queryString = queryString;
	}

	@Override
	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
}
