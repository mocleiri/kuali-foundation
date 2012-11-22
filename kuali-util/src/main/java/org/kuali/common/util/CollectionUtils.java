package org.kuali.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class CollectionUtils {

	public static final Object[] toArray(List<Object> objects) {
		return objects.toArray(new Object[objects.size()]);
	}

	public static final boolean isEmpty(Collection<?> c) {
		return c == null || c.size() == 0;
	}

	public static final List<String> getTrimmedListFromCSV(String csv) {
		List<String> list = new ArrayList<String>();
		if (!StringUtils.isBlank(csv)) {
			String[] tokens = Str.splitAndTrimCSV(csv);
			list.addAll(Arrays.asList(tokens));
		}
		return list;
	}
}
