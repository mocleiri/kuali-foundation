package org.kuali.common.util.xml.jaxb;

import org.kuali.common.util.Assert;

public final class Class {

	public Class(String name, double fee) {
		Assert.noBlanks(name);
		Assert.isTrue(fee >= 0, "fee is negative");
		this.name = name;
		this.fee = fee;
	}

	private final String name;
	private final double fee;

	public String getName() {
		return name;
	}

	public double getFee() {
		return fee;
	}

}
