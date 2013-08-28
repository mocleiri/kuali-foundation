package org.kuali.common.util.xml.jaxb.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;

import com.google.common.collect.Lists;

@XmlRootElement
public class ListWrapper<T> {

	@XmlAnyElement(lax = true)
	private final List<T> list;

	ListWrapper() {
		this(Lists.<T> newArrayList());
	}

	public ListWrapper(List<T> list) {
		Assert.noNulls(list);
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

}
