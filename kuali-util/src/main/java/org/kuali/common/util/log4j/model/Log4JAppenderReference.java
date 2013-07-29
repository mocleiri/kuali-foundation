package org.kuali.common.util.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Log4JAppenderReference {

	String name;

	public Log4JAppenderReference(Log4JAppenderReference reference) {
		super();
		this.name = reference.getName();
	}

	public Log4JAppenderReference() {
		this((String) null);
	}

	public Log4JAppenderReference(String name) {
		super();
		this.name = name;
	}

	@XmlAttribute(name = "ref")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
