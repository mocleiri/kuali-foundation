package org.kuali.common.util.xml.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.google.common.base.Optional;

public class OmitOptionalAdapter<T> extends XmlAdapter<T, Optional<T>> {

	@Override
	public T marshal(Optional<T> optional) {
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public Optional<T> unmarshal(T value) {
		if (value == null) {
			return Optional.<T> absent();
		} else {
			return Optional.<T> of(value);
		}
	}

}
