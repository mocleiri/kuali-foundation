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
package org.kuali.common.core.validate.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.kuali.common.util.property.ImmutableProperties;

import com.google.common.collect.ImmutableMap;

/**
 * All field's on the annotated class that are map's must extend from Guava {@code ImmutableMap}
 */
@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@ValidType(superType = Map.class, type = ImmutableMap.class, exclude = ImmutableProperties.class)
@Documented
public @interface ImmutableGuavaMaps {

	String message() default "maps must be immutable";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
