package org.kuali.common.util.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @deprecated
 */
@Deprecated
public class Param {

	String name;
	String value;

	public Param(Param param) {
		super();
		this.name = param.getName();
		this.value = param.getValue();
	}

	public Param() {
		this(null, null);
	}

	public Param(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	@XmlAttribute
	public String getValue() {
		return value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
