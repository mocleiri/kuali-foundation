package org.kuali.common.util.base;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;

public class PresentPredicate<T> implements Predicate<Optional<T>> {

	@Override
	public boolean apply(Optional<T> reference) {
		checkNotNull(reference, "reference");
		return reference.isPresent();
	}

}
