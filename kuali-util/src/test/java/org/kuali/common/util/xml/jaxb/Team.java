package org.kuali.common.util.xml.jaxb;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

public class Team {

	public Team() {
		this(NullUtils.NONE, NullUtils.NONE);
	}

	public Team(String name, String color) {
		Assert.noBlanks(name, color);
		this.name = name;
		this.color = color;
	}

	private final String name;
	private final String color;

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

}
