package org.kuali.common.util.bind.api;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ TYPE, FIELD })
@Retention(RUNTIME)
@Documented
public @interface BindPrefix {

	String value() default "";

	Class<?> type() default void.class;

	boolean none() default false;

}
