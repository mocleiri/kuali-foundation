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
import static org.kuali.common.util.ReflectionUtils.extractFieldValue;
import static org.kuali.common.util.ReflectionUtils.isStringKeyedMap;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import org.kuali.common.core.validate.annotation.IgnoreBlanks;
import org.kuali.common.core.validate.annotation.NoBlankMapKeys;
import org.kuali.common.util.CollectionUtils;

import com.google.common.base.Optional;

public class NoBlankMapKeysValidator extends AbstractFieldsValidator<NoBlankMapKeys, Object> {

	@Override
	protected Optional<String> validate(Field field, Object instance) {

		// Might be ignoring blanks on this particular field
		if (field.getAnnotation(IgnoreBlanks.class) != null) {
			return absent();
		}

		// This field may not be a Map<String,?>
		if (!isStringKeyedMap(field)) {
			return absent();
		}

		// Extract the value of the field into an optional
		Optional<?> fieldValue = extractFieldValue(field, instance);

		// If there is no value for this field, we are done
		if (!fieldValue.isPresent()) {
			return absent();
		}

		// We know the field is a map that uses strings for it's keys at this point
		@SuppressWarnings("unchecked")
		Map<String, ?> map = (Map<String, ?>) fieldValue.get();

		// Extract any entries with a blank key
		Collection<String> blanks = CollectionUtils.getBlanks(map.keySet());
		if (blanks.size() > 0) {
			return Validation.errorMessage(field, "contains " + blanks.size() + " blank keys");
		} else {
			return absent();
		}
	}
}
