package org.kuali.common.util.csv;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface CsvTypeAdapter {

	@SuppressWarnings("rawtypes")
	Class<? extends CsvAdapter> value();

}
