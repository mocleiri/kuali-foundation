package org.kuali.common.util.xml.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;

import org.kuali.common.util.Assert;

public class ListWrapper<T> {

	private final List<T> list;

	public ListWrapper() {
		this(new ArrayList<T>());
	}

	public ListWrapper(List<T> list) {
		Assert.noNulls(list);
		this.list = list;
	}

	@XmlAnyElement(lax = true)
	public List<T> getList() {
		return list;
	}

}
