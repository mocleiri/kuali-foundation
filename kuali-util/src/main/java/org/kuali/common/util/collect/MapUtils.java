package org.kuali.common.util.collect;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

public class MapUtils {

	private MapUtils() {
	}

	public static Set<String> getBlankKeys(Map<String, ?> map) {
		Preconditions.checkNotNull(map, "'map' cannot be null");
		Set<String> blanks = new HashSet<String>();
		for (String key : map.keySet()) {
			if (StringUtils.isBlank(key)) {
				blanks.add(key);
			}
		}
		return blanks;
	}

}
