package org.kuali.common.util.xml.jaxb;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.util.CollectionUtils;

public class ImmutableListWrapperAdapter<T> extends XmlAdapter<ListWrapper<T>, List<T>> {

	private List<T> EMPTY = Collections.<T> emptyList();
	private ListWrapper<T> EMPTY_WRAPPER = new ListWrapper<T>(EMPTY);

	@Override
	public ListWrapper<T> marshal(List<T> list) {
		if (CollectionUtils.isEmpty(list)) {
			return EMPTY_WRAPPER;
		} else {
			return new ListWrapper<T>(list);
		}
	}

	@Override
	public List<T> unmarshal(ListWrapper<T> wrapper) {
		if (CollectionUtils.isEmpty(wrapper.getList())) {
			return EMPTY;
		} else {
			return Collections.unmodifiableList(wrapper.getList());
		}
	}

}
