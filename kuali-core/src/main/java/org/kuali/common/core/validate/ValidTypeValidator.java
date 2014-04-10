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
import static org.kuali.common.util.ReflectionUtils.isSuperType;
import static org.kuali.common.util.ReflectionUtils.validateIsSuperType;

import java.lang.reflect.Field;

import org.kuali.common.core.validate.annotation.ValidType;

import com.google.common.base.Optional;

public class ValidTypeValidator extends AbstractFieldsValidator<ValidType, Object> {

	private Class<?> superType; // eg java.util.Map
	private Class<?> type; // eg com.google.common.collect.ImmutableMap
	private Class<?>[] excludes;

	@Override
	public void initialize(ValidType constraintAnnotation) {
		this.superType = constraintAnnotation.superType();
		this.type = constraintAnnotation.type();
		this.excludes = constraintAnnotation.exclude();
		// Make sure type descends from superType
		validateIsSuperType(superType, type);
	}

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// Figure out if we even need to validate this field
		if (isSkip(field)) {
			// If not, there is nothing more to do
			return absent();
		}

		// Otherwise, make sure it descends from the correct type
		if (isSuperType(type, field.getType())) {
			// If it does, we are good to go
			return absent();
		} else {
			// If not, return an error message
			String fieldType = field.getType().getCanonicalName();
			String suffix = "Invalid type: [" + fieldType + "] must descend from (or be) [" + type.getCanonicalName() + "]";
			return Validation.errorMessage(field, suffix);
		}

	}

	/**
	 * Return true if this field needs validation, false otherwise
	 */
	protected boolean isSkip(Field field) {

		// If this field does not descend from superType, we can skip checking it's type
		if (!isSuperType(superType, field.getType())) {
			return true;
		}

		// If it is an explicitly configured exclusion, we can skip checking its type
		for (Class<?> exclusion : excludes) {
			if (field.getType() == exclusion) {
				return true;
			}
		}

		// If we get here we need to validate this field
		return false;

	}

}
