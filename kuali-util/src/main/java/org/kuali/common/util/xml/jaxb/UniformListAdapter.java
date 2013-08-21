package org.kuali.common.util.xml.jaxb;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.ListUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

public class UniformListAdapter<T> extends XmlAdapter<T[], List<T>> {

	private final List<T> EMPTY = Collections.<T> emptyList();

	@Override
	public final T[] marshal(List<T> list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return newArray(list);
		}
	}

	@Override
	public final List<T> unmarshal(T[] array) {
		if (array == null || array.length == 0) {
			return EMPTY;
		} else {
			return Collections.unmodifiableList(Arrays.asList(array));
		}
	}

	protected T[] newArray(List<T> list) {
		ListUtils.assertUniformRuntimeType(list);
		Class<?> type = list.get(0).getClass();
		int modifiers = type.getModifiers();
		Assert.isTrue(Modifier.isFinal(modifiers), "[" + type.getName() + "] is not final");
		// T[] newArray = newArray(list.size(), (T[]) null);
		// for (int i = 0; i < list.size(); i++) {
		// newArray[i] = list.get(i);
		// }
		// return newArray;
		return null;
	}

	// @SafeVarargs
	// protected T[] newArray(int length, T... array) {
	// return Arrays.copyOf(array, length);
	// }

}
