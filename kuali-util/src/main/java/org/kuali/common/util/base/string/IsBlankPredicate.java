package org.kuali.common.util.base.string;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.google.common.base.Predicate;

// enum singleton pattern
public enum IsBlankPredicate implements Predicate<String> {

	INSTANCE;

	@Override
	public boolean apply(String string) {
		checkNotNull(string);
		return isBlank(string);
	}

}
