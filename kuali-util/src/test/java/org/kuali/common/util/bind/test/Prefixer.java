package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkState;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.bind.api.BindingPrefix;

import com.google.common.base.Optional;

public class Prefixer {

	public static Optional<String> get(Class<?> type, Optional<BindingPrefix> annotation) {
		return get(Optional.<String> absent(), type, annotation);
	}

	public static Optional<String> get(Optional<String> prefix, Class<?> type, Optional<BindingPrefix> annotation) {
		// Explicit prefix. This overrides everything
		if (prefix.isPresent()) {
			return prefix;
		}

		// No annotation, use the uncapitalized form of the simple class name as a prefix
		if (!annotation.isPresent()) {
			return Optional.of(get(type));
		}

		// Extract the annotation
		BindingPrefix bindingPrefix = annotation.get();

		// They have specifically said, "don't use a prefix"
		if (bindingPrefix.none()) {
			return Optional.absent();
		}

		if (!bindingPrefix.type().equals(void.class)) {
			// An explicit prefix class has been configured on the annotation. This overrides value()
			String actualPrefix = get(bindingPrefix.type());
			return Optional.of(actualPrefix);
		}

		// Check the annotation to see if value() is still at its default value
		if (bindingPrefix.value().equals(BindingPrefix.DEFAULT)) {
			// This can actually happen 2 different ways
			// 1 - They didn't supply a value and thus the annotation is still at its default
			// 2 - They did supply a value but the value they supplied was the default value
			// In either case, we switch to using the uncapitalized version of the simple class name as the prefix
			// They can set the annotation attribute 'none=true' to explicitly prevent a prefix from being used
			return Optional.of(get(type));
		} else {
			// Make sure they haven't supplied a blank prefix
			checkState(!StringUtils.isBlank(bindingPrefix.value()), "[%s.value()] cannot be blank", bindingPrefix.getClass().getCanonicalName());
			// An explicit prefix has been configured on the annotation.
			return Optional.of(bindingPrefix.value());
		}

	}

	public static String get(Class<?> type) {
		return StringUtils.uncapitalize(type.getSimpleName());
	}

}
