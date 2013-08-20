package org.kuali.common.util.log.log4j.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.log.log4j.model.Debug;

public class DebugAdapter extends XmlAdapter<String, Debug> {

	@Override
	public final String marshal(Debug value) {
		if (Debug.DEFAULT_VALUE.equals(value)) {
			return null;
		} else {
			return value.name().toLowerCase();
		}
	}

	@Override
	public final Debug unmarshal(String value) {
		if (value == null) {
			return Debug.DEFAULT_VALUE;
		} else {
			return Debug.valueOf(value.toUpperCase());
		}
	}

}
