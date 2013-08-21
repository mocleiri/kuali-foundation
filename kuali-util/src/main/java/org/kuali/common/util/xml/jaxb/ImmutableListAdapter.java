package org.kuali.common.util.xml.jaxb;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.util.CollectionUtils;

public class ImmutableListAdapter<T> extends XmlAdapter<ListWrapper<T>, List<T>> {

	private List<T> EMPTY_LIST = Collections.<T> emptyList();
	private ListWrapper<T> EMPTY_WRAPPER = new ListWrapper<T>(EMPTY_LIST);

	@Override
	public ListWrapper<T> marshal(List<T> list) {
		if (list == null || list.size() == 0) {
			return EMPTY_WRAPPER;
		} else {
			return new ListWrapper<T>(list);
		}
	}

	@Override
	public List<T> unmarshal(ListWrapper<T> wrapper) {
		if (CollectionUtils.isEmpty(wrapper.getList())) {
			return EMPTY_LIST;
		} else {
			return Collections.unmodifiableList(wrapper.getList());
		}
	}

}
