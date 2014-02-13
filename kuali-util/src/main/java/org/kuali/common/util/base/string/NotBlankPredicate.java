package org.kuali.common.util.base.string;

import static org.apache.commons.lang3.StringUtils.isBlank;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

// enum singleton pattern
public enum NotBlankPredicate implements Predicate<String> {

	INSTANCE;

	@Override
	public boolean apply(@Nullable String string) {
		return !isBlank(string);
	}

}
