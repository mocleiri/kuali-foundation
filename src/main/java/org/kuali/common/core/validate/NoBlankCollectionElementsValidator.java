package org.kuali.common.core.validate;

import static com.google.common.base.Optional.absent;
import static org.kuali.common.util.ReflectionUtils.extractFieldValue;
import static org.kuali.common.util.ReflectionUtils.isStringCollection;

import java.lang.reflect.Field;
import java.util.Collection;

import org.kuali.common.core.validate.annotation.IgnoreBlanks;
import org.kuali.common.core.validate.annotation.NoBlankCollectionElements;
import org.kuali.common.util.CollectionUtils;

import com.google.common.base.Optional;

public class NoBlankCollectionElementsValidator extends AbstractFieldsValidator<NoBlankCollectionElements, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// Might be ignoring blanks on this particular field
		if (field.getAnnotation(IgnoreBlanks.class) != null) {
			return absent();
		}

		// This field may not be Collection<String>
		if (!isStringCollection(field)) {
			return absent();
		}

		// Extract the value of the field into an optional
		Optional<?> fieldValue = extractFieldValue(field, instance);

		// If there is no value for this field, we are done
		if (!fieldValue.isPresent()) {
			return absent();
		}

		// The ReflectionUtils.isStringCollection() check above ensures that this field is a Collection<String>
		@SuppressWarnings("unchecked")
		Collection<String> collection = (Collection<String>) fieldValue.get();

		// Return an error message if there are blank String's inside the collection
		Collection<String> blanks = CollectionUtils.getBlanks(collection);
		if (blanks.size() > 0) {
			return Validation.errorMessage(field, "contains " + blanks.size() + " blank strings");
		} else {
			return absent();
		}

	}

}
