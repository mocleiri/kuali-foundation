package org.kuali.common.util.spring.env.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.kuali.common.util.spring.env.converter.Converter;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnvConversion {

	Class<? extends Converter<?, ?>> value();

}
