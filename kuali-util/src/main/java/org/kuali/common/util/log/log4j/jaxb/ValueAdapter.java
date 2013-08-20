package org.kuali.common.util.log.log4j.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.log.log4j.model.Threshold;

public class ValueAdapter extends XmlAdapter<String, String> {

	@Override
	public final String marshal(String value) {
		Threshold v = Threshold.valueOf(value.toUpperCase());
		if (Threshold.DEFAULT_VALUE.equals(v)) {
			return null;
		} else {
			return value;
		}
	}

	@Override
	public final String unmarshal(String value) {
		if (value == null) {
			return Threshold.DEFAULT_VALUE.name().toLowerCase();
		} else {
			return value;
		}
	}

}
