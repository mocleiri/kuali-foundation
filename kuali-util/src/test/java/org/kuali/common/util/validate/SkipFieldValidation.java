package org.kuali.common.util.validate;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

@Target({ FIELD })
@Documented
public @interface SkipFieldValidation {
}
