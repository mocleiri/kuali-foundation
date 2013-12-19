package org.kuali.common.util.validate;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.kuali.common.util.Ports;

@Min(Ports.MIN)
@Max(Ports.MAX)
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface ValidPort {

	String message() default "Port must be an integer between " + Ports.MIN + " and " + Ports.MAX + " (inclusive)";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
