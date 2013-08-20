package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DropTrueAdapter extends XmlAdapter<Boolean, Boolean> {

	@Override
	public Boolean marshal(Boolean value) {
		if (Boolean.TRUE.equals(value)) {
			return null;
		} else {
			return value;
		}
	}

	@Override
	public Boolean unmarshal(Boolean value) {
		if (value == null) {
			return Boolean.TRUE;
		} else {
			return value;
		}
	}

}
