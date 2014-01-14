package org.kuali.common.util.validate;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Any fields that extend from {@code java.util.Collection} and are parameterized as type {@code String} must not contain the empty string or pure whitespace
 */
@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NoBlankCollectionElementsValidator.class)
@Documented
public @interface NoBlankCollectionElements {

	String message() default "blank strings not allowed";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
