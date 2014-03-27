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

import java.lang.reflect.Field;

import org.kuali.common.core.validate.annotation.NoNullFields;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class FinalFieldsValidator extends AbstractFieldsValidator<NoNullFields, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {
		if (ReflectionUtils.isFinal(field)) {
			return absent();
		} else {
			return Validation.errorMessage(field, "is not final");
		}
	}
}
