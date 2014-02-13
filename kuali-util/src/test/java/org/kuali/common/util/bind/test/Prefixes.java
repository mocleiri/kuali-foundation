package org.kuali.common.util.bind.test;

import static com.google.common.base.Optional.absent;
import static org.kuali.common.util.Annotations.extractFieldAnnotation;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.lang.reflect.Field;

import org.kuali.common.util.bind.api.Bind;

import com.google.common.base.Optional;

public class Prefixes {

	public static Optional<String> get(Field field) {
		Optional<String> prefix = getPrefix(extractFieldAnnotation(field, Bind.class));
		if (prefix.isPresent()) {
			return prefix;
		}
		return Optional.of(field.getName());
	}

	protected static Optional<String> getPrefix(Optional<Bind> annotation) {
		if (!annotation.isPresent()) {
			return absent();
		}
		String value = annotation.get().value();
		checkNotBlank(value, Bind.class.getCanonicalName() + ".value()");
		if (Bind.ABSENT.equals(value)) {
			return absent();
		} else {
			return Optional.of(value);
		}
	}

}
