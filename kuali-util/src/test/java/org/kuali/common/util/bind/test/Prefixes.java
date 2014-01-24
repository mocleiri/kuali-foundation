package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkState;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Annotations;
import org.kuali.common.util.bind.api.BindingPrefix;

import com.google.common.base.Optional;

public class Prefixes {

	public static Optional<String> get(Class<?> type) {
		Optional<BindingPrefix> annotation = Annotations.get(type, BindingPrefix.class);
		if (!annotation.isPresent()) {
			return Optional.of(getPrefix(type));
		}
		return null;
	}

	public static Optional<String> get(BindingPrefix annotation) {
		if (annotation.none()) {
			return Optional.absent();
		}

		if (!annotation.type().equals(void.class)) {
			// An explicit prefix class has been configured on the annotation. This overrides value()
			String actualPrefix = getPrefix(annotation.type());
			return Optional.of(actualPrefix);
		}

		// Check the annotation to see if value() is still at its default value
		if (annotation.value().equals(BindingPrefix.DEFAULT)) {
			// This can happen 2 different ways
			// 1 - They didn't supply a value and thus the annotation is still at its default
			// 2 - They did supply a value but the value they supplied was the default value
			// In either case, we return the absence of a prefix
			return Optional.absent();
		} else {
			// Make sure they haven't supplied a blank prefix
			checkState(!StringUtils.isBlank(annotation.value()), "[%s.value()] cannot be blank", annotation.getClass().getCanonicalName());
			// An explicit prefix has been configured on the annotation.
			return Optional.of(annotation.value());
		}

	}

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
			return Optional.of(getPrefix(type));
		}

		// Extract the annotation
		BindingPrefix bindingPrefix = annotation.get();

		// If there is a prefix on the annotation, use it
		Optional<String> annotationPrefix = get(bindingPrefix);
		if (annotationPrefix.isPresent()) {
			return annotationPrefix;
		} else {
			return Optional.of(getPrefix(type));
		}

	}

	protected static String getPrefix(Class<?> type) {
		return StringUtils.uncapitalize(type.getSimpleName());
	}

}
