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
		return getList(null, csv);
	}

	public static final List<String> getList(List<String> list, String csv) {
		List<String> newList = new ArrayList<String>();
		if (!isEmpty(list)) {
			newList.addAll(list);
		}
		if (!StringUtils.isBlank(csv)) {
			newList.addAll(Arrays.asList(Str.splitAndTrimCSV(csv)));
		}
		return newList;
	}

}
