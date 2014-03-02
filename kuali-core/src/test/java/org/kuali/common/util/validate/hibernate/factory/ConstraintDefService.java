package org.kuali.common.util.validate.hibernate.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.hibernate.validator.cfg.ConstraintDef;

public interface ConstraintDefService {

	boolean supports(Class<? extends Annotation> annotationType);

	ConstraintDef<?, ?> getConstraintDef(Field annotatedField, Class<? extends Annotation> annotationType);

	ConstraintDef<?, ?> getConstraintDef(Class<?> annotatedClass, Class<? extends Annotation> annotationType);

}