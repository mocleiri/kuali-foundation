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

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

/**
 * <p>
 * This class contains boilerplate code for examining every declared field for a given object's class hierarchy.
 * </p>
 * 
 * <p>
 * It recursively traverses the class hierarchy to acquire an exhaustive list of every declared field in the hierarchy and provides a hook for validating each field in turn.
 * </p>
 */
public abstract class AbstractFieldsValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {

	@Override
	public void initialize(A constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {
		checkNotNull(object, "'object' cannot be null");
		checkNotNull(constraintContext, "'constraintContext' cannot be null");
		Set<Field> fields = ReflectionUtils.getAllFields(object.getClass());
		List<String> errors = Lists.newArrayList();
		for (Field field : fields) {
			Optional<String> error = validate(field, object);
			if (error.isPresent()) {
				errors.add(error.get());
			}
		}
		if (errors.size() == 0) {
			return true;
		} else {
			constraintContext.disableDefaultConstraintViolation();
			for (String error : errors) {
				constraintContext.buildConstraintViolationWithTemplate(error).addConstraintViolation();
			}
			return false;
		}
	}

	protected abstract Optional<String> validate(Field field, Object instance);

}
