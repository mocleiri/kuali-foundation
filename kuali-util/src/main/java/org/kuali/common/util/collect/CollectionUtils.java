package org.kuali.common.util.collect;

import java.util.ArrayList;
import java.util.Collection;

import org.kuali.common.util.nullify.NullUtils;

public class CollectionUtils {

	private CollectionUtils() {
	}

	public static Collection<String> getBlanks(Collection<?> collection) {
		Collection<String> blanks = new ArrayList<String>();
		for (Object element : collection) {
			if (element == null) {
				blanks.add(null);
			} else if (NullUtils.isBlank(element)) {
				CharSequence charSequence = (CharSequence) element;
				blanks.add(charSequence.toString());
			}
		}
		return blanks;
	}
}
