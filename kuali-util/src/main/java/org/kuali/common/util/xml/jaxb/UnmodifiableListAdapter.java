package org.kuali.common.util.xml.jaxb;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class UnmodifiableListAdapter<T> extends XmlAdapter<List<T>, List<T>> {

	private final List<T> EMPTY = Collections.<T> emptyList();

	@Override
	public final List<T> marshal(List<T> list) {
		return toEmpty(list);
	}

	@Override
	public final List<T> unmarshal(List<T> list) {
		return toEmpty(list);
	}

	protected List<T> toEmpty(List<T> list) {
		if (list == null || list.size() == 0) {
			return EMPTY;
		} else {
			return Collections.unmodifiableList(list);
		}
	}

}
