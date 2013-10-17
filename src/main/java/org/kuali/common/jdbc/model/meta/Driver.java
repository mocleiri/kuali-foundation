package org.kuali.common.jdbc.model.meta;

import org.kuali.common.util.Assert;

public final class Driver {

	public Driver(String name, String version) {
		Assert.noBlanks(name, version);
		this.name = name;
		this.version = version;
	}

	private final String name;
	private final String version;

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

}
