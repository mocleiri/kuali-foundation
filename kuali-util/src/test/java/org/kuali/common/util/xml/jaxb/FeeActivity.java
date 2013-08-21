package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;

public abstract class FeeActivity {

	public static final Double FREE = Double.valueOf(-1);

	@SuppressWarnings("unused")
	private FeeActivity() {
		this(FREE);
	}

	public FeeActivity(double fee) {
		Assert.isTrue(fee == FREE || fee >= 0, "invalid fee");
		this.fee = fee;
	}

	@XmlAttribute
	@XmlJavaTypeAdapter(FeeAdapter.class)
	private final Double fee;

	public double getFee() {
		return fee;
	}

}
