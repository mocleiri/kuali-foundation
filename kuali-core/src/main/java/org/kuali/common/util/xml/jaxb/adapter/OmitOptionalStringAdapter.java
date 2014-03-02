package org.kuali.common.util.xml.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.google.common.base.Optional;

public class OmitOptionalStringAdapter extends XmlAdapter<String, Optional<String>> {

	@Override
	public String marshal(Optional<String> optional) {
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public Optional<String> unmarshal(String value) {
		if (value == null) {
			return Optional.<String> absent();
		} else {
			return Optional.<String> of(value);
		}
	}

}
