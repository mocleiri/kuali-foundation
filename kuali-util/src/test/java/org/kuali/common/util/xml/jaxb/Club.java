package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

@XmlRootElement
public final class Club {

	@SuppressWarnings("unused")
	private Club() {
		this(NullUtils.NONE);
	}

	public Club(String name) {
		this(name, 0);
	}

	public Club(String name, double fee) {
		Assert.noBlanks(name);
		Assert.isTrue(fee >= 0, "fee is negative");
		this.name = name;
		this.fee = fee;
	}

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final double fee;

	public String getName() {
		return name;
	}

	public double getFee() {
		return fee;
	}

}
