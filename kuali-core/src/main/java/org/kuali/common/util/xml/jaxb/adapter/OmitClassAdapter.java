package org.kuali.common.util.xml.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.Assert;

public abstract class OmitClassAdapter extends XmlAdapter<Class<?>, Class<?>> {

	public OmitClassAdapter(Class<?> omitClass) {
		Assert.noNulls(omitClass);
		this.omitClass = omitClass;
	}

	private final Class<?> omitClass;

	@Override
	public final Class<?> marshal(Class<?> value) {
		if (omitClass == value) {
			return null;
		} else {
			return value;
		}
	}

	@Override
	public final Class<?> unmarshal(Class<?> value) {
		if (value == null) {
			return omitClass;
		} else {
			return value;
		}
	}

	public final Class<?> getOmitClass() {
		return omitClass;
	}

}
