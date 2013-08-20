package org.kuali.common.util.log.log4j.jaxb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public abstract class UnmodifiableListAdapter<T> extends XmlAdapter<T[], List<T>> {

	private final List<T> EMPTY_LIST = Collections.<T> emptyList();

	protected abstract T[] getArrayFromNonEmptyList(List<T> list);

	@Override
	public T[] marshal(List<T> list) {
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return getArrayFromNonEmptyList(list);
		}
	}

	@Override
	public List<T> unmarshal(T[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_LIST;
		} else {
			return Collections.unmodifiableList(Arrays.asList(array));
		}
	}
}
