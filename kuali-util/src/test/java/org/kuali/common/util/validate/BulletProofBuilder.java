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
@Constraint(validatedBy = {})
@NoNullFields
@NoBlanks
@MatchDeclaringClassFields
@Documented
public @interface BulletProofBuilder {

	String message() default "bullet proof builder checks failed";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
