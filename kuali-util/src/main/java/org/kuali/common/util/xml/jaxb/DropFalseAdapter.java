package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DropFalseAdapter extends XmlAdapter<Boolean, Boolean> {

	@Override
	public Boolean marshal(Boolean value) {
		if (Boolean.FALSE.equals(value)) {
			return null;
		} else {
			return value;
		}
	}

	@Override
	public Boolean unmarshal(Boolean value) {
		if (value == null) {
			return Boolean.FALSE;
		} else {
			return value;
		}
	}

}
