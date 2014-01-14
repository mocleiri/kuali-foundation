package org.kuali.common.util.validate;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidRuntimeTypeValidator.class)
@Documented
public @interface ValidRuntimeType {

	String message() default "invalid runtime type";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<?> type();

	Class<?> superType();

}
