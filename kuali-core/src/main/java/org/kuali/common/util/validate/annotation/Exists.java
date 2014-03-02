package org.kuali.common.util.validate.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.kuali.common.util.validate.FileExistsValidator;
import org.kuali.common.util.validate.LocationExistsValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = { LocationExistsValidator.class, FileExistsValidator.class })
@Documented
public @interface Exists {

	String message() default "resource does not exist";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
