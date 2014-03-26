package org.kuali.common.core.validate.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Used to indicate an exception can be made on a particular field with regards to the presence of a blank string
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Documented
public @interface IgnoreBlanks {
}
