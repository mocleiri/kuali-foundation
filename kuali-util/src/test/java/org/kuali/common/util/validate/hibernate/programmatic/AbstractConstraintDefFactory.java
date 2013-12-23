package org.kuali.common.util.validate.hibernate.programmatic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.hibernate.validator.cfg.ConstraintDef;

import com.google.common.base.Optional;

public abstract class AbstractConstraintDefFactory<C extends ConstraintDef<C, A>, A extends Annotation> implements ConstraintDefFactory<C, A> {

	@Override
	public Optional<C> getConstraintDef(Class<A> annotationType, Field annotatedField) {
		A annotation = annotatedField.getAnnotation(getAnnotationType());
		return getOptional(annotation);
	}

	@Override
	public Optional<C> getConstraintDef(Class<A> annotationType, Class<?> annotatedClass) {
		A annotation = annotatedClass.getAnnotation(getAnnotationType());
		return getOptional(annotation);
	}

	protected abstract C getConstraintDef(A annotation);

	protected Optional<C> getOptional(A annotation) {
		if (annotation == null) {
			return Optional.absent();
		} else {
			C constraintDef = getConstraintDef(annotation);
			return Optional.of(constraintDef);
		}
	}
}
