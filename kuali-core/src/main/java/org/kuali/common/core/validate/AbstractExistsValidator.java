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

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.kuali.common.core.validate.annotation.Exists;

public abstract class AbstractExistsValidator<T> implements ConstraintValidator<Exists, T> {

	@Override
	public void initialize(Exists constraintAnnotation) {
	}

	protected boolean validate(boolean valid, String path, ConstraintValidatorContext context) {
		if (!valid) {
			String error = String.format("[%s] does not exist", path);
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error).addConstraintViolation();
		}
		return valid;
	}

}
