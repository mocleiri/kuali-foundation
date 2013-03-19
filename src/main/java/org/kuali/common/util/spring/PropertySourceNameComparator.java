package org.kuali.common.util.spring;

import java.util.Comparator;

import org.springframework.core.env.PropertySource;

public class PropertySourceNameComparator implements Comparator<PropertySource<?>> {

	@Override
	public int compare(PropertySource<?> o1, PropertySource<?> o2) {
		String name1 = o1.getName();
		String name2 = o2.getName();
		return name1.compareTo(name2);
	}

}
