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
@NoBlankStrings
@NoBlankOptionals
@NoBlankMapKeys
@NoBlankCollectionElements
@Documented
public @interface NoBlanks {

	String message() default "blank strings not allowed";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
