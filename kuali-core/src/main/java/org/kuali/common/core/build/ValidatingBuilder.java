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
package org.kuali.common.core.build;

import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.core.validate.Validation.checkConstraints;

import java.util.List;

import javax.validation.Validator;

import org.apache.commons.lang3.builder.Builder;
import org.kuali.common.core.validate.Validation;

/**
 * Concrete builder that hooks into the {@code javax.validation.Validator} validation framework to enable validating objects before they are built
 */
public abstract class ValidatingBuilder<T> implements Builder<T> {

	protected Validator validator = Validation.getDefaultValidator();
	protected List<Class<?>> validationGroups = newArrayList();

	protected T validate(T instance) {
		return checkConstraints(instance, validator, validationGroups);
	}

	public ValidatingBuilder<T> withValidator(Validator validator) {
		this.validator = validator;
		return this;
	}

	public ValidatingBuilder<T> withValidationGroups(List<Class<?>> validationGroups) {
		this.validationGroups = validationGroups;
		return this;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public List<Class<?>> getValidationGroups() {
		return validationGroups;
	}

	public void setValidationGroups(List<Class<?>> validationGroups) {
		this.validationGroups = validationGroups;
	}

}
