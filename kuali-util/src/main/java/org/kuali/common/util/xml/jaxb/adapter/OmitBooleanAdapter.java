package org.kuali.common.util.xml.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public abstract class OmitBooleanAdapter extends XmlAdapter<Boolean, Boolean> {

	public OmitBooleanAdapter(boolean omitValue) {
		this.omitValue = omitValue;
	}

	private final Boolean omitValue;

	@Override
	public final Boolean marshal(Boolean value) {
		if (omitValue.equals(value)) {
			return null;
		} else {
			return value;
		}
	}

	@Override
	public final Boolean unmarshal(Boolean value) {
		if (value == null) {
			return omitValue;
		} else {
			return value;
		}
	}

	public final boolean getOmitValue() {
		return omitValue;
	}

}
