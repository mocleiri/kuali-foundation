package org.kuali.common.util.spring.format;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.spring.format.FormatTokens.EMPTY_PROPERTIES_TOKEN;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface XmlPropertiesFormat {

	String encoding() default UTF8;

	String emptyPropertiesToken() default EMPTY_PROPERTIES_TOKEN;

}
