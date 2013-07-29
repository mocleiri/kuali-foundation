package org.kuali.common.util.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Log4JParam {

	String name;
	String value;

	public Log4JParam() {
		this(null, null);
	}

	public Log4JParam(String name, String value) {
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
