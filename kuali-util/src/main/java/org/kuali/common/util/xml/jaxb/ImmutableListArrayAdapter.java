package org.kuali.common.util.xml.jaxb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.util.CollectionUtils;

public abstract class ImmutableListArrayAdapter<T> extends XmlAdapter<T[], List<T>> {

	private final List<T> EMPTY = Collections.<T> emptyList();

	protected abstract T[] getArrayFromNonEmptyList(List<T> list);

	@Override
	public final T[] marshal(List<T> list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return getArrayFromNonEmptyList(list);
		}
	}

	@Override
	public final List<T> unmarshal(T[] array) {
		if (array == null || array.length == 0) {
			return EMPTY;
		} else {
			return Collections.unmodifiableList(Arrays.asList(array));
		}
	}

}
