/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util;

import static com.google.common.base.Optional.fromNullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.google.common.base.Optional;

public class Annotations {

	public static <T extends Annotation> Optional<T> extractClassAnnotation(Class<?> type, Class<T> annotationClass) {
		return fromNullable(type.getAnnotation(annotationClass));
	}

	public static <T extends Annotation> Optional<T> extractFieldAnnotation(Field field, Class<T> annotationClass) {
		return fromNullable(field.getAnnotation(annotationClass));
	}

}
