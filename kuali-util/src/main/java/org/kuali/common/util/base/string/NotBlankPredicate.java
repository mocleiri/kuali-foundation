package org.kuali.common.util.base.string;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;

// enum singleton pattern
public enum NotBlankPredicate implements Predicate<Optional<String>> {

	INSTANCE;

	@Override
	public boolean apply(Optional<String> optional) {
		if (optional.isPresent()) {
			return !isBlank(optional.get());
		} else {
			return true;
		}
	}

}
