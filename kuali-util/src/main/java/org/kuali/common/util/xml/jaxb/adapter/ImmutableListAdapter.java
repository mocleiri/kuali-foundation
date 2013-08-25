package org.kuali.common.util.xml.jaxb.adapter;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.xml.jaxb.wrapper.ListWrapper;

public class ImmutableListAdapter<T> extends XmlAdapter<ListWrapper<T>, List<T>> {

	private final List<T> EMPTY_LIST = Collections.emptyList();
	private final ListWrapper<T> EMPTY_WRAPPER = new ListWrapper<T>(EMPTY_LIST);

	@Override
	public ListWrapper<T> marshal(List<T> list) {
		System.out.println("1");
		if (isEmpty(list)) {
			System.out.println("2");
			return EMPTY_WRAPPER;
		} else {
			System.out.println("3");
			return new ListWrapper<T>(list);
		}
	}

	@Override
	public List<T> unmarshal(ListWrapper<T> wrapper) {
		System.out.println("4");
		if (isEmpty(wrapper.getList())) {
			System.out.println("5");
			return EMPTY_LIST;
		} else {
			System.out.println("6");
			return Collections.unmodifiableList(wrapper.getList());
		}
	}

	protected static boolean isEmpty(List<?> list) {
		return list == null || list.size() == 0;
	}

}
