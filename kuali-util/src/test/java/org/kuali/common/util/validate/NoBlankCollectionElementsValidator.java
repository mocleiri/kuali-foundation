package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.Collection;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class NoBlankCollectionElementsValidator extends AbstractFieldsValidator<NoBlankCollectionElements, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// This field may not be Collection<String>
		if (!ReflectionUtils.isStringCollection(field)) {
			return Optional.absent();
		}

		// Extract the value of the field into an optional
		Optional<?> fieldValue = ReflectionUtils.get(field, instance);

		// If there is no value for this field, we are done
		if (!fieldValue.isPresent()) {
			return Optional.absent();
		}

		// The ReflectionUtils.isStringCollection() check above ensures that this field is a Collection<String>
		@SuppressWarnings("unchecked")
		Collection<String> collection = (Collection<String>) fieldValue.get();

		// Return an error message if there are blank String's inside the collection
		Collection<String> blanks = CollectionUtils.getBlanks(collection);
		if (blanks.size() > 0) {
			return Validation.errorMessage(field, "contains " + blanks.size() + " blank strings");
		} else {
			return Optional.absent();
		}

	}

}
