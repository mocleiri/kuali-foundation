package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;

@XmlRootElement
public final class Sport {

	@XmlAttribute
	private final String name;

	public String getName() {
		return name;
	}

	Sport() {
		this.name = null;
	}

	public Sport(String name) {
		Assert.noBlanks(name);
		this.name = name;
	}

}
