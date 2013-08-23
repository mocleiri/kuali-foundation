package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public abstract class OmitIntegerAdapter extends XmlAdapter<Integer, Integer> {

	public OmitIntegerAdapter(int omitValue) {
		this.omitValue = omitValue;
	}

	private final Integer omitValue;

	@Override
	public final Integer marshal(Integer value) {
		if (omitValue.equals(value)) {
			return null;
		} else {
			return value;
		}
	}

	@Override
	public final Integer unmarshal(Integer value) {
		if (value == null) {
			return omitValue;
		} else {
			return value;
		}
	}

	public final int getOmitValue() {
		return omitValue;
	}

}
