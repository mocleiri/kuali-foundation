package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.Map;

import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.collect.MapUtils;

import com.google.common.base.Optional;

public class NoBlankMapEntriesValidator extends AbstractFieldsValidator<NoBlankMapEntries, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// This field may not be a map
		if (!ReflectionUtils.isMap(field)) {
			return Optional.absent();
		}

		// Extract the value of the field into an optional
		Optional<?> fieldValue = ReflectionUtils.get(field, instance);

		// If there is no value for this field, we are done
		if (!fieldValue.isPresent()) {
			return Optional.absent();
		}

		// We know the value of the field is a map at this point
		Map<?, ?> map = (Map<?, ?>) fieldValue.get();

		// Extract any entries with a blank key or value
		Map<?, ?> blanks = MapUtils.getBlankEntries(map);
		if (blanks.size() > 0) {
			return ValidationUtils.errorMessage(field, "contains " + blanks.size() + " entries with a blank key or value");
		} else {
			return Optional.absent();
		}
	}
}
