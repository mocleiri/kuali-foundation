package org.kuali.common.util.xml.jaxb;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public abstract class UnmodifiableListAdapter<T> extends XmlAdapter<List<T>, List<T>> {

	public UnmodifiableListAdapter(boolean dropValue) {
		this.dropValue = dropValue;
	}

	private final Boolean dropValue;

	@Override
	public final List<T> marshal(List<T> list) {
		return list;
	}

	@Override
	public final List<T> unmarshal(List<T> list) {
		if (list == null) {
			return Collections.<T> emptyList();
		} else {
			return Collections.unmodifiableList(list);
		}
	}

	public final Boolean getDropValue() {
		return dropValue;
	}

}
