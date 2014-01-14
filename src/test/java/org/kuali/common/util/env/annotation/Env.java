package org.kuali.common.util.env.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ TYPE })
@Retention(RUNTIME)
@Documented
public @interface Env {

	public static final String NOPREFIX = "";

	String prefix() default NOPREFIX;

	boolean skip() default false;

	boolean includeInheritedFields() default true;

}
