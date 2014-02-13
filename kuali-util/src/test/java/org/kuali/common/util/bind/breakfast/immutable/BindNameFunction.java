package org.kuali.common.util.bind.breakfast.immutable;

import static com.google.common.base.Optional.absent;
import static org.apache.commons.lang3.StringUtils.uncapitalize;
import static org.kuali.common.util.Annotations.extractFieldAnnotation;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.lang.reflect.Field;

import org.kuali.common.util.bind.api.Bind;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public class BindNameFunction implements Function<Field, Optional<String>> {

	public static BindNameFunction make() {
		return new BindNameFunction();
	}

	@Override
	public Optional<String> apply(Field field) {
		checkNotNull(field, "field");
		Optional<Bind> optional = extractFieldAnnotation(field, Bind.class);
		if (!optional.isPresent()) {
			return Optional.of(uncapitalize(field.getName()));
		}
		String value = optional.get().value();
		checkNotBlank(value, "value");
		if (Bind.ABSENT.equals(value)) {
			return absent();
		}
		if (Bind.DEFAULT.equals(value)) {
			return Optional.of(uncapitalize(field.getName()));
		} else {
			return Optional.of(value);
		}
	}

}
