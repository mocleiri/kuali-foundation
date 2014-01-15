package org.kuali.common.util.validate;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated class must not contain any fields whose runtime value is {@code null}
 */
@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NoNullFieldsValidator.class)
@Documented
public @interface NoNullFields {

	String message() default "null not allowed";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
