package org.kuali.common.util.xml.jaxb;

import org.kuali.common.util.Assert;

public final class Sport {

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
