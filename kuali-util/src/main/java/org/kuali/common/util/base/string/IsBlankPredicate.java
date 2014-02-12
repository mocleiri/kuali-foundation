package org.kuali.common.util.base.string;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.google.common.base.Predicate;

public class IsBlankPredicate implements Predicate<String> {

	@Override
	public boolean apply(String string) {
		checkNotNull(string);
		return isBlank(string);
	}

}
