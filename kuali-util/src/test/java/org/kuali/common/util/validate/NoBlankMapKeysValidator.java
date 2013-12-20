package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class NoBlankMapKeysValidator extends AbstractFieldsValidator<NoBlankMapKeys, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// This field may not be a map
		if (!ReflectionUtils.isStringKeyedMap(field)) {
			return Optional.absent();
		}

		// Extract the value of the field into an optional
		Optional<?> fieldValue = ReflectionUtils.get(field, instance);

		// If there is no value for this field, we are done
		if (!fieldValue.isPresent()) {
			return Optional.absent();
		}

		// We know the field is a map that uses strings for it's keys at this point
		@SuppressWarnings("unchecked")
		Map<String, ?> map = (Map<String, ?>) fieldValue.get();

		// Extract any entries with a blank key or value
		Collection<String> blanks = CollectionUtils.getBlanks(map.keySet());
		if (blanks.size() > 0) {
			return ValidationUtils.errorMessage(field, "contains " + blanks.size() + " blank keys");
		} else {
			return Optional.absent();
		}
	}
}
