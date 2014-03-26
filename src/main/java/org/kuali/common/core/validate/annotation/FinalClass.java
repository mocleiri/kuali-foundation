package org.kuali.common.core.validate.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.kuali.common.core.validate.FinalClassValidator;

/**
 * The annotated class must be declared as {@code final}
 */
@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = FinalClassValidator.class)
@Documented
public @interface FinalClass {

	String message() default "class must be final";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
