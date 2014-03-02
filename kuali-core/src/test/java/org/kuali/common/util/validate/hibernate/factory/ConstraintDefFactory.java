package org.kuali.common.util.validate.hibernate.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.hibernate.validator.cfg.ConstraintDef;

public interface ConstraintDefFactory<C extends ConstraintDef<C, A>, A extends Annotation> {

	Class<A> getAnnotationType();

	C getConstraintDef(Field annotatedField);

	C getConstraintDef(Class<?> annotatedClass);

}
