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
@Constraint(validatedBy = UniqueFieldNamesValidator.class)
@Documented
public @interface UniqueFieldNames {

	String message() default "field names must be unique";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
