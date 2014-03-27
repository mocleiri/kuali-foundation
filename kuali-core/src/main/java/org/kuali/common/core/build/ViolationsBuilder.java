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

import java.util.Set;

import javax.validation.ConstraintViolation;

/**
 * Extension of {@code ValidatingBuilder} that provides information about constraint violations without throwing an exception
 */
public abstract class ViolationsBuilder<T> extends ValidatingBuilder<T> {

	private static final Class<?>[] EMPTY_CLASS_ARRAY = {};

	public abstract Set<ConstraintViolation<T>> violations();

	protected Set<ConstraintViolation<T>> violations(T instance) {
		return validator.validate(instance, validationGroups.toArray(EMPTY_CLASS_ARRAY));
	}

}
