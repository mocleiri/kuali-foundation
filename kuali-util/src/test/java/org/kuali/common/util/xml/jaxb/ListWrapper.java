package org.kuali.common.util.xml.jaxb;

import java.util.List;

import org.kuali.common.util.Assert;

public class ListWrapper<T> {

	private final List<T> list;

	public ListWrapper(List<T> list) {
		Assert.noNulls(list);
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

}
