package org.kuali.common.util.validate.hibernate.factory;

import static org.kuali.common.util.ReflectionUtils.getDeclarationPath;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.hibernate.validator.cfg.ConstraintDef;
import org.hibernate.validator.cfg.GenericConstraintDef;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

public abstract class AbstractConstraintDefFactory<C extends ConstraintDef<C, A>, A extends Annotation> implements ConstraintDefFactory<C, A> {

	private final String cname = getAnnotationType().getCanonicalName();

	@Override
	public C getConstraintDef(Field annotatedField) {
		Optional<A> optional = Optional.of(annotatedField.getAnnotation(getAnnotationType()));
		String path = getDeclarationPath(annotatedField.getDeclaringClass());
		Preconditions.checkState(optional.isPresent(), "[%s.%s] is not annotated with [%s]", path, annotatedField.getName(), cname);
		return getConstraintDef(optional.get());
	}

	@Override
	public C getConstraintDef(Class<?> annotatedClass) {
		Optional<A> optional = Optional.of(annotatedClass.getAnnotation(getAnnotationType()));
		Preconditions.checkState(optional.isPresent(), "[%s] is not annotated with [%s]", getDeclarationPath(annotatedClass), cname);
		return getConstraintDef(optional.get());
	}

	protected abstract C getConstraintDef(A annotation);
	
	public static <A extends Annotation> GenericConstraintDef<A> newGenericConstraintDef(Class<A> annotationType) {
		return new GenericConstraintDef<A>(annotationType);
	}

}
