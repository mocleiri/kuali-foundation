package org.kuali.common.util.collect;

import java.util.Map;

import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Preconditions;

public class MapUtils {

	private MapUtils() {
	}

	public static boolean isMap(Class<?> type) {
		return Map.class.isAssignableFrom(type);
	}

	public static CheckMapResult checkForBlanks(Map<?, ?> map) {
		Preconditions.checkNotNull(map, "'map' cannot be null");
		int badKeys = 0;
		int badValues = 0;
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			badKeys = NullUtils.isBlank(entry.getKey()) ? badKeys++ : badKeys;
			badValues = NullUtils.isBlank(entry.getValue()) ? badValues++ : badValues;
		}
		return new CheckMapResult(badKeys, badValues);
	}

	public static CheckMapResult checkForNullsOrBlanks(Map<?, ?> map) {
		Preconditions.checkNotNull(map, "'map' cannot be null");
		int badKeys = 0;
		int badValues = 0;
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			badKeys = NullUtils.isNullOrBlank(entry.getKey()) ? badKeys++ : badKeys;
			badValues = NullUtils.isNullOrBlank(entry.getValue()) ? badValues++ : badValues;
		}
		return new CheckMapResult(badKeys, badValues);
	}

}
