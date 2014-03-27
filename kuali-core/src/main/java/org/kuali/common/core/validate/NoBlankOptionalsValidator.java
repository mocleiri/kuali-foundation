/**
 * Copyright 2014-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.core.validate;

import static com.google.common.base.Optional.absent;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.kuali.common.util.ReflectionUtils.extractFieldValue;
import static org.kuali.common.util.ReflectionUtils.isOptionalString;

import java.lang.reflect.Field;

import org.kuali.common.core.validate.annotation.IgnoreBlanks;
import org.kuali.common.core.validate.annotation.NoBlankOptionals;

import com.google.common.base.Optional;

public class NoBlankOptionalsValidator extends AbstractFieldsValidator<NoBlankOptionals, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// Might be ignoring blanks on this particular field
		if (field.getAnnotation(IgnoreBlanks.class) != null) {
			return absent();
		}

		// If this field isn't an Optional<String> we are good to go
		if (!isOptionalString(field)) {
			return absent();
		}

		// Get the value of the field as an optional
		Optional<?> fieldValue = extractFieldValue(field, instance);

		// If the field was null we are good to go
		if (!fieldValue.isPresent()) {
			return absent();
		}

		// Extract the object inside the field (we know it's an Optional<String> at this point)
		@SuppressWarnings("unchecked")
		Optional<String> optional = (Optional<String>) fieldValue.get();

		// If there is no value inside the optional we are good to go
		if (!optional.isPresent()) {
			return absent();
		}

		// Extract the string from the optional
		String string = optional.get();

		// If it's not blank, we are good to go
		if (!isBlank(string)) {
			return absent();
		}

		// Otherwise, return an error message
		return Validation.errorMessage(field, "optional string value cannot be blank");
	}

}
