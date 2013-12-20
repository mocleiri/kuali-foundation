package org.kuali.common.util.validate;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.google.common.collect.ImmutableMap;

/**
 * All field's on the annotated class that are map's must be a Guava {@code ImmutableMap} at runtime
 * 
 * Fields representing a {@code Map} that are {@code null} are considered valid.
 */
@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@ValidRuntimeType(base = Map.class, required = ImmutableMap.class)
@Documented
public @interface ImmutableGuavaMaps {

	String message() default "maps must be immutable";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
