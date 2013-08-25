package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

@XmlRootElement
public class Team {

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final String color;

	public Team() {
		this(NullUtils.NONE, NullUtils.NONE);
	}

	public Team(String name, String color) {
		Assert.noBlanks(name, color);
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

}
