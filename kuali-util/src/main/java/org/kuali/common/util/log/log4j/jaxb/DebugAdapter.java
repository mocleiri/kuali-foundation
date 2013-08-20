package org.kuali.common.util.log.log4j.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.log.log4j.model.Debug;

public abstract class DebugAdapter extends XmlAdapter<String, String> {

	@Override
	public final String marshal(String value) {
		Debug debug = Debug.valueOf(value.toUpperCase());
		if (Debug.DEFAULT_VALUE.equals(debug)) {
			return null;
		} else {
			return debug.name().toLowerCase();
		}
	}

	@Override
	public final String unmarshal(String value) {
		if (value == null) {
			return Debug.DEFAULT_VALUE.name().toLowerCase();
		} else {
			return Debug.valueOf(value).name().toLowerCase();
		}
	}

}
