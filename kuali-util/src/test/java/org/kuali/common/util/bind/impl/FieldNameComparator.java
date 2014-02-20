package org.kuali.common.util.bind.impl;

import java.lang.reflect.Field;
import java.util.Comparator;

// singleton enum pattern
public enum FieldNameComparator implements Comparator<Field> {
	INSTANCE;

	@Override
	public int compare(Field one, Field two) {
		return one.getName().compareTo(two.getName());
	}

}
