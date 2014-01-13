package org.kuali.common.util.validate;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * All fields in the annotated class must be declared as {@code final}
 */
@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = FinalFieldsValidator.class)
@Documented
public @interface FinalClass {

	String message() default "fields must be final";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
