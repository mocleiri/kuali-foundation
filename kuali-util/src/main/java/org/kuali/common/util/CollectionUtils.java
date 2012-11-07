package org.kuali.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class CollectionUtils {

	public static final boolean isEmpty(Collection<?> c) {
		return c == null || c.size() == 0;
	}

	public static final List<String> getListFromCSV(String csv) {
		List<String> list = new ArrayList<String>();
		if (!StringUtils.isBlank(csv)) {
			list.addAll(Arrays.asList(Str.splitAndTrimCSV(csv)));
		}
		return list;
	}
}
