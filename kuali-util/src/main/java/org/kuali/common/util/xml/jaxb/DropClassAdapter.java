package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.Assert;

public abstract class DropClassAdapter extends XmlAdapter<Class<?>, Class<?>> {

	public DropClassAdapter(Class<?> dropValue) {
		Assert.noNulls(dropValue);
		this.dropValue = dropValue;
	}

	private final Class<?> dropValue;

	@Override
	public final Class<?> marshal(Class<?> value) {
		if (dropValue.equals(value)) {
			return null;
		} else {
			return value;
		}
	}

	@Override
	public final Class<?> unmarshal(Class<?> value) {
		if (value == null) {
			return dropValue;
		} else {
			return value;
		}
	}

	public final Class<?> getDropValue() {
		return dropValue;
	}

}
