package org.kuali.common.util.validate.hibernate.programmatic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.hibernate.validator.cfg.ConstraintDef;

import com.google.common.base.Optional;

public interface ConstraintDefFactory<C extends ConstraintDef<C, A>, A extends Annotation> {

	Class<A> getAnnotatedType();

	Optional<C> getConstraintDef(Class<A> annotationType, Field field);

	Optional<C> getConstraintDef(Class<A> annotationType, Class<?> type);

}
