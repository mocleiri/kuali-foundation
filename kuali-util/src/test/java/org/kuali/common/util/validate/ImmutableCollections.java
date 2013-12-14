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
@Constraint(validatedBy = ImmutableMapsValidator.class)
@Documented
public @interface ImmutableCollections {

	String message() default "collections must be immutable";

	Class<?>[] groups() default {};

	boolean skip() default false;

	boolean includeInheritedFields() default true;

	Class<? extends Payload>[] payload() default {};

}
