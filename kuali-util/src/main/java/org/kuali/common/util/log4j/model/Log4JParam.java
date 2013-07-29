package org.kuali.common.util.log4j.model;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
