package org.kuali.common.util.xml.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.google.common.base.Optional;

public class OmitOptionalIntegerAdapter extends XmlAdapter<Integer, Optional<Integer>> {

	@Override
	public Integer marshal(Optional<Integer> optional) {
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public Optional<Integer> unmarshal(Integer value) {
		if (value == null) {
			return Optional.<Integer> absent();
		} else {
			return Optional.<Integer> of(value);
		}
	}

}
