package org.kuali.common.util.env.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnvOverride {

	public static final String NOPREFIX = "";

	String prefix() default NOPREFIX;

	String[] keys() default {};

	boolean skip() default false;

}
