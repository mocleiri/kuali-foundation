package org.kuali.common.util.spring.format;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface StringListFormat {

	String separator() default ",";

	boolean trim() default false;

	boolean omitEmpty() default false;

	String empty() default "";

}
