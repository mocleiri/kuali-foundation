package org.kuali.common.util.spring.format.optional;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.kuali.common.util.spring.format.FormatTokens.ABSENT_OPTIONAL_TOKEN;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface OptionalStringFormat {

	String absentToken() default ABSENT_OPTIONAL_TOKEN;

}
