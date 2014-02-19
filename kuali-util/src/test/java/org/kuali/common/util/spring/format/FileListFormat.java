package org.kuali.common.util.spring.format;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.kuali.common.util.spring.convert.Tokens;

@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface FileListFormat {

	String emptyListToken() default Tokens.EMPTY_LIST_TOKEN;

}
