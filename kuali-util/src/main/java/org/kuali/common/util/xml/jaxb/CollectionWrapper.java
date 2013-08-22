package org.kuali.common.util.xml.jaxb;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAnyElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ListUtils;

public class CollectionWrapper<T> {

	@XmlAnyElement(lax = true)
	private final Collection<T> c;

	@SuppressWarnings("unused")
	private CollectionWrapper() {
		this(ListUtils.<T> newArrayList());
	}

	public CollectionWrapper(Collection<T> c) {
		Assert.noNulls(c);
		this.c = c;
	}

	public Collection<T> getCollection() {
		return c;
	}

}
