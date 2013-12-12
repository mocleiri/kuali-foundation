package org.kuali.common.util.validate;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PortValidator.class)
@Documented
public @interface Port {

	String message() default "Port must be an integer between " + PortValidator.MIN + " and " + PortValidator.MAX + " (inclusive)";

	Class<?>[] groups() default {};

	boolean skip() default false;

	Class<? extends Payload>[] payload() default {};

}
