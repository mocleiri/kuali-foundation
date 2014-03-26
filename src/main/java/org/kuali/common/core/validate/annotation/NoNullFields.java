package org.kuali.common.core.validate.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.kuali.common.core.validate.NoNullFieldsValidator;

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
