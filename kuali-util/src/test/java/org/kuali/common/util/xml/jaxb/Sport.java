package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class Sport {

	@XmlAttribute
	private final String name;

	public String getName() {
		return name;
	}

	@SuppressWarnings("unused")
	private Sport() {
		this(null);
	}

	public Sport(String name) {
		this.name = name;
	}

}
