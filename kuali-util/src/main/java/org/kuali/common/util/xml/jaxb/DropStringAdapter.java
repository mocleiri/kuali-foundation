package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.Assert;

public abstract class DropStringAdapter extends XmlAdapter<String, String> {

	public DropStringAdapter(String dropValue) {
		Assert.noNullsWithMsg("dropValue is required", dropValue);
		this.dropValue = dropValue;
	}

	private final String dropValue;

	@Override
	public final String marshal(String value) {
		if (dropValue.equals(value)) {
			return null;
		} else {
			return value;
		}
	}

	@Override
	public final String unmarshal(String value) {
		if (value == null) {
			return dropValue;
		} else {
			return value;
		}
	}

	public final String getDropValue() {
		return dropValue;
	}

}
