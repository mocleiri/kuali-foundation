package org.kuali.common.util.xml.jaxb;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ImmutableCollectionAdapter<T> extends XmlAdapter<CollectionWrapper<T>, Collection<T>> {

	private final List<T> EMPTY_COLLECTION = Collections.<T> emptyList();
	private final CollectionWrapper<T> EMPTY_WRAPPER = new CollectionWrapper<T>(EMPTY_COLLECTION);

	@Override
	public CollectionWrapper<T> marshal(Collection<T> c) {
		if (isEmpty(c)) {
			return EMPTY_WRAPPER;
		} else {
			return new CollectionWrapper<T>(c);
		}
	}

	@Override
	public Collection<T> unmarshal(CollectionWrapper<T> wrapper) {
		if (isEmpty(wrapper.getCollection())) {
			return EMPTY_COLLECTION;
		} else {
			return Collections.unmodifiableCollection(wrapper.getCollection());
		}
	}

	protected static boolean isEmpty(Collection<?> c) {
		return c == null || c.size() == 0;
	}

}
