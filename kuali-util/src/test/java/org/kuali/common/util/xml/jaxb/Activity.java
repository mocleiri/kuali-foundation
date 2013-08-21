package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;

public abstract class Activity {

	public static final Double UNKNOWN = Double.valueOf(-1);

	@SuppressWarnings("unused")
	private Activity() {
		this(UNKNOWN);
	}

	public Activity(double fee) {
		Assert.isTrue(fee == UNKNOWN || fee >= 0, "invalid fee");
		this.fee = fee;
	}

	@XmlAttribute
	@XmlJavaTypeAdapter(FeeAdapter.class)
	private final Double fee;

	public double getFee() {
		return fee;
	}

}
