package org.kuali.common.util.collect;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

public class CollectionUtils {

	private CollectionUtils() {
	}

	public static Collection<String> getBlanks(Collection<String> collection) {
		Collection<String> blanks = new ArrayList<String>();
		for (String element : collection) {
			if (StringUtils.isBlank(element)) {
				blanks.add(element);
			}
		}
		return blanks;
	}
}
