package org.kuali.common.util.env.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.kuali.common.util.env.adapter.EnvironmentAdapter;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnvAdapter {

	Class<? extends EnvironmentAdapter<?, ?>> value();

}
