package org.kuali.common.util.xml.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public abstract class OmitBooleanAdapter extends XmlAdapter<String, Boolean> {

	public OmitBooleanAdapter(boolean omitValue) {
		this.omitValue = omitValue;
	}

	private final Boolean omitValue;

	@Override
	public final String marshal(Boolean value) {
		if (omitValue.equals(value)) {
			return null;
		} else {
			return value.toString();
		}
	}

	@Override
	public final Boolean unmarshal(String value) {
		if (value == null) {
			return omitValue;
		} else {
			return Boolean.parseBoolean(value);
		}
	}

	public final boolean getOmitValue() {
		return omitValue;
	}

}
