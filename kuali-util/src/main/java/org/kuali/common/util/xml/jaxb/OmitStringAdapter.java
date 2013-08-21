package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.Assert;

public abstract class OmitStringAdapter extends XmlAdapter<String, String> {

	public OmitStringAdapter(String omitValue) {
		Assert.noNulls((Object) omitValue);
		this.omitValue = omitValue;
	}

	private final String omitValue;

	@Override
	public final String marshal(String value) {
		if (omitValue.equals(value)) {
			return null;
		} else {
			return value;
		}
	}

	@Override
	public final String unmarshal(String value) {
		if (value == null) {
			return omitValue;
		} else {
			return value;
		}
	}

	public final String getOmitValue() {
		return omitValue;
	}

}
