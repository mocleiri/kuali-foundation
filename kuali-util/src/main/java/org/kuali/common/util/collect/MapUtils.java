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

	public static CheckMapResult checkForBlanks(Map<?, ?> map) {
		Preconditions.checkNotNull(map, "'map' cannot be null");
		int badKeys = 0;
		int badValues = 0;
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			badKeys = isBlank(entry.getKey()) ? badKeys++ : badKeys;
			badValues = isBlank(entry.getValue()) ? badValues++ : badValues;
		}
		return new CheckMapResult(badKeys, badValues);
	}

	protected static boolean isBlank(Object object) {
		return (object instanceof CharSequence) && StringUtils.isBlank((CharSequence) object);
	}

	protected static boolean isNullOrBlank(Object object) {
		if (object == null) {
			return true;
		} else {
			return isBlank(object);
		}
	}

	public static CheckMapResult checkForNullsOrBlanks(Map<?, ?> map) {
		Preconditions.checkNotNull(map, "'map' cannot be null");
		int badKeys = 0;
		int badValues = 0;
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			badKeys = isNullOrBlank(entry.getKey()) ? badKeys++ : badKeys;
			badValues = isNullOrBlank(entry.getValue()) ? badValues++ : badValues;
		}
		return new CheckMapResult(badKeys, badValues);
	}

}
