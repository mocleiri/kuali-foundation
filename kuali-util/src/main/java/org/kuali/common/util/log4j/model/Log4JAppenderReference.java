package org.kuali.common.util.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Log4JAppenderReference {

	String name;

	@XmlAttribute(name = "ref")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
