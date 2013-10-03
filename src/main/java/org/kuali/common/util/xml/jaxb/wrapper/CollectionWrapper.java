package org.kuali.common.util.xml.jaxb.wrapper;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAnyElement;

import org.kuali.common.util.Assert;

import com.google.common.collect.Lists;

public class CollectionWrapper<T> {

	@XmlAnyElement(lax = true)
	private final Collection<T> collection;

	@SuppressWarnings("unused")
	private CollectionWrapper() {
		this(Lists.<T> newArrayList());
	}

	public CollectionWrapper(Collection<T> collection) {
		Assert.noNulls(collection);
		this.collection = collection;
	}

	public Collection<T> getCollection() {
		return collection;
	}

}
