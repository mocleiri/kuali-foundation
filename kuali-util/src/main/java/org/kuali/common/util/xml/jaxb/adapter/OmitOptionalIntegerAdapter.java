package org.kuali.common.util.xml.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.google.common.base.Optional;

public class OmitOptionalIntegerAdapter extends XmlAdapter<String, Optional<Integer>> {

	@Override
	public String marshal(Optional<Integer> optional) {
		if (optional.isPresent()) {
			return optional.get().toString();
		} else {
			return null;
		}
	}

	@Override
	public Optional<Integer> unmarshal(String value) {
		if (value == null) {
			return Optional.<Integer> absent();
		} else {
			return Optional.<Integer> of(Integer.parseInt(value));
		}
	}

}
