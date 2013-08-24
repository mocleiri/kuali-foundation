package org.kuali.common.util.xml.jaxb.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ListUtils;

public class ListWrapper<T> {

	@XmlAnyElement(lax = true)
	private final List<T> list;

	@SuppressWarnings("unused")
	private ListWrapper() {
		this(ListUtils.<T> newArrayList());
	}

	public ListWrapper(List<T> list) {
		Assert.noNulls(list);
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

}
