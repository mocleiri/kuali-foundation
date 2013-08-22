package org.kuali.common.util.xml.jaxb;

import java.util.Collection;
import java.util.Collections;

import javax.xml.bind.annotation.XmlAnyElement;

import org.kuali.common.util.Assert;

public class CollectionWrapper<T> {

	@XmlAnyElement(lax = true)
	private final Collection<T> c;

	@SuppressWarnings("unused")
	private CollectionWrapper() {
		this(Collections.<T> emptyList());
	}

	public CollectionWrapper(Collection<T> c) {
		Assert.noNulls(c);
		this.c = c;
	}

	public Collection<T> getCollection() {
		return c;
	}

}
