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

import java.lang.reflect.Field;

import org.kuali.common.core.validate.annotation.IgnoreBlanks;
import org.kuali.common.core.validate.annotation.NoBlankStrings;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class NoBlankStringsValidator extends AbstractFieldsValidator<NoBlankStrings, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// Might be ignoring blanks on this particular field
		if (field.getAnnotation(IgnoreBlanks.class) != null) {
			return absent();
		}

		// This field may not be a String
		if (!ReflectionUtils.isString(field)) {
			return absent();
		}

		// Extract the value of the field into an optional
		Optional<?> fieldValue = extractFieldValue(field, instance);

		// We know the field contains a string at this point
		String string = (String) fieldValue.orNull();

		// Null is ok
		if (string == null) {
			return absent();
		}

		// Non-null value cannot be blank
		if (isBlank(string)) {
			return Validation.errorMessage(field, "blank strings not allowed");
		} else {
			return absent();
		}
	}

}
