package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public abstract class DropBooleanAdapter extends XmlAdapter<Boolean, Boolean> {

	public DropBooleanAdapter(boolean dropValue) {
		this.dropValue = dropValue;
	}

	private final Boolean dropValue;

	@Override
	public final Boolean marshal(Boolean value) {
		if (dropValue.equals(value)) {
			return null;
		} else {
			return value;
		}
	}

	@Override
	public final Boolean unmarshal(Boolean value) {
		if (value == null) {
			return dropValue;
		} else {
			return value;
		}
	}

	public final Boolean getDropValue() {
		return dropValue;
	}

}
