package org.kuali.common.util.spring.format;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.kuali.common.util.nullify.NullUtils;

@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface PathListFormat {

	boolean trim() default true;

	boolean omitEmpty() default true;

	String magicEmptyString() default NullUtils.NONE;

}
