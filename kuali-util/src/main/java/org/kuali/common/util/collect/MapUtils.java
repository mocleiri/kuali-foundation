package org.kuali.common.util.collect;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

public class MapUtils {

	private MapUtils() {
	}

	public static boolean isMap(Class<?> type) {
		return Map.class.isAssignableFrom(type);
	}

	public static CheckForBlanksResult checkForBlanks(Map<?, ?> map) {
		Preconditions.checkNotNull(map, "'map' cannot be null");
		int blankKeyCount = 0;
		int blankValueCount = 0;
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			Object key = entry.getKey();
			Object val = entry.getValue();
			if (key instanceof CharSequence) {
				blankKeyCount = StringUtils.isBlank((CharSequence) key) ? blankKeyCount++ : blankKeyCount;
			}
			if (val instanceof CharSequence) {
				blankValueCount = StringUtils.isBlank((CharSequence) val) ? blankValueCount++ : blankValueCount;
			}
		}
		return new CheckForBlanksResult(blankKeyCount, blankValueCount);
	}

}
