package org.kuali.common.core.validate.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.kuali.common.core.validate.NoBlankOptionalsValidator;

/**
 * 
 */
@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NoBlankOptionalsValidator.class)
@Documented
public @interface NoBlankOptionals {

	String message() default "optional value cannot be blank";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
