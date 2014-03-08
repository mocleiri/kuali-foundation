package org.kuali.common.core.validate.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Aggregate of the annotations:
 * 
 * <pre>
 *  StronglyImmutable
 *  NoNullFields
 *  NoBlanks
 *  ImmutableGuavaMaps
 *  ImmutableGuavaCollections
 * </pre>
 */
@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@StronglyImmutable
@NoNullFields
@NoBlanks
@ImmutableGuavaMaps
@ImmutableGuavaCollections
@Documented
public @interface IdiotProofImmutable {

	String message() default "immutability checks failed";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
