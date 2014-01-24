package org.kuali.common.util.bind.test;

import java.lang.reflect.Field;
import java.util.Comparator;

public final class FieldNameComparator implements Comparator<Field> {

	public static final FieldNameComparator INSTANCE = new FieldNameComparator();

	private FieldNameComparator() {
	}

	@Override
	public int compare(Field one, Field two) {
		return one.getName().compareTo(two.getName());
	}

}
