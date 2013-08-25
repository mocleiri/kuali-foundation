package org.kuali.common.util.xml.jaxb.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public class ListStringWrapper {

	@XmlAnyElement(lax = true)
	private final List<String> strings;

	@SuppressWarnings("unused")
	private ListStringWrapper() {
		this(ImmutableList.<String> of());
	}

	public ListStringWrapper(List<String> strings) {
		Assert.noNulls(strings);
		this.strings = strings;
	}

	public List<String> getStrings() {
		return strings;
	}

}
