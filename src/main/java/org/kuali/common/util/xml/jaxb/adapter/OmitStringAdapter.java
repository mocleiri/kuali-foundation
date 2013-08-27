package org.kuali.common.util.xml.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.Assert;

public abstract class OmitStringAdapter extends XmlAdapter<String, String> {

	public OmitStringAdapter(String omitString) {
		Assert.notNull(omitString);
		this.omitString = omitString;
	}

	private final String omitString;

	@Override
	public final String marshal(String string) {
		if (omitString.equals(string)) {
			return null;
		} else {
			return string;
		}
	}

	@Override
	public final String unmarshal(String string) {
		if (string == null) {
			return omitString;
		} else {
			return string;
		}
	}

	public final String getOmitString() {
		return omitString;
	}

}
