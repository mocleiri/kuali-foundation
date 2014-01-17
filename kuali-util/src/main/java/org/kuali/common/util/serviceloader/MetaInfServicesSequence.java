package org.kuali.common.util.serviceloader;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ TYPE })
@Retention(RUNTIME)
public @interface MetaInfServicesSequence {

	public static final int FIRST = Integer.MAX_VALUE;
	public static final int MIDDLE = 0;
	public static final int LAST = Integer.MAX_VALUE;

	int value() default MIDDLE;

}
