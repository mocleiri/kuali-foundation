package org.kuali.common.core.validate.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated class must be declared final, and all fields declared in the class must be final.
 */
@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@FinalClass
@FinalFields
@Documented
public @interface StronglyImmutable {

	String message() default "strongly immutable checks failed";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
