package org.kuali.common.util.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

public class AppenderRef {

	String ref;

	public AppenderRef(AppenderRef reference) {
		super();
		this.ref = reference.getRef();
	}

	public AppenderRef() {
		this((String) null);
	}

	public AppenderRef(String name) {
		super();
		this.ref = name;
	}

	@XmlAttribute
	public String getRef() {
		return ref;
	}

	public void setRef(String name) {
		this.ref = name;
	}

}
