package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.Comparator;

public class FieldComparator implements Comparator<Field> {

	@Override
	public int compare(Field one, Field two) {
		return one.getName().compareTo(two.getName());
	}

}
