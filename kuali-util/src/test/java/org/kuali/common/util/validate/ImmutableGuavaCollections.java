package org.kuali.common.util.validate;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collection;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.google.common.collect.ImmutableCollection;

/**
 * All field's on the annotated class that are collection's must be a Guava {@code ImmutableCollection} at runtime
 * 
 * Fields representing a {@code Collection} that are {@code null} are considered valid.
 */
@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@ValidRuntimeType(base = Collection.class, required = ImmutableCollection.class)
@Documented
public @interface ImmutableGuavaCollections {

	String message() default "collections must be immutable";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
