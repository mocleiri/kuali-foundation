package org.kuali.common.util.validate.hibernate.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.hibernate.validator.cfg.ConstraintDef;

import com.google.common.base.Optional;

public interface ConstraintDefFactory<C extends ConstraintDef<C, A>, A extends Annotation> {

	Class<A> getAnnotationType();

	Optional<C> getConstraintDef(Field annotatedField);

	Optional<C> getConstraintDef(Class<?> annotatedClass);

}
