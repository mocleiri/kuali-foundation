package org.kuali.common.util.validate.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.kuali.common.util.validate.PortValidator;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PortValidator.class)
@Documented
public @interface Port {

	String message() default "Port must be an integer between 0 and 65535 (inclusive)";

	Class<?>[] groups() default {};

	boolean skip() default false;

	Class<? extends Payload>[] payload() default {};

}
