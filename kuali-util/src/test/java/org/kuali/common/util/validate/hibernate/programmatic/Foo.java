package org.kuali.common.util.validate.hibernate.programmatic;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Min;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintDef;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.validate.MatchDeclaringClassFields;
import org.kuali.common.util.validate.NoNullFields;
import org.kuali.common.util.validate.ValidationUtils;
import org.kuali.common.util.validate.hibernate.factory.ConstraintDefFactory;
import org.kuali.common.util.validate.hibernate.factory.MinDefFactory;

import com.google.common.base.Optional;

@NoNullFields
public class Foo {

	@Min(0)
	private final int weight;
	private final String foo;

	private Foo(Builder builder) {
		this.foo = builder.foo;
		this.weight = builder.weight;
	}

	public static Builder builder() {
		return new Builder();
	}

	@MatchDeclaringClassFields
	public static class Builder {

		private String foo;
		private int weight;

		private void validate(Builder builder) {
			try {
				Map<Class<? extends Annotation>, ConstraintDefFactory<? extends ConstraintDef<? extends ConstraintDef<?, ?>, ? extends Annotation>, ? extends Annotation>> factories = new HashMap<Class<? extends Annotation>, ConstraintDefFactory<? extends ConstraintDef<? extends ConstraintDef<?, ?>, ? extends Annotation>, ? extends Annotation>>();
				factories.put(Min.class, new MinDefFactory());
				HibernateValidatorConfiguration configuration = Validation.byProvider(HibernateValidator.class).configure();
				Set<Field> fields = ReflectionUtils.getAllFields(builder.getClass().getDeclaringClass());
				for (Field field : fields) {
					Annotation[] annotations = field.getAnnotations();
					for (Annotation annotation : annotations) {
						if (ValidationUtils.isConstraint(annotation)) {
							Class<? extends Annotation> annotationType = annotation.annotationType();
							ConstraintDefFactory<? extends ConstraintDef<?, ?>, ?> factory = factories.get(annotationType);
							Optional<? extends ConstraintDef<?, ?>> optional = factory.getConstraintDef(field);
							if (optional.isPresent()) {
								ConstraintDef<?, ?> cdef = optional.get();
								ConstraintMapping cm = configuration.createConstraintMapping();
								cm.type(builder.getClass()).property(field.getName(), ElementType.FIELD).constraint(cdef);
								configuration.addMapping(cm);
							}
						}
					}
				}
				Validator validator = configuration.buildValidatorFactory().getValidator();
				Set<ConstraintViolation<Builder>> violations = validator.validate(builder);
				check(violations);
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}

		private void validate(Foo instance) {
			Validator validator = ValidationUtils.getDefaultValidator();
			check(validator.validate(instance));
		}

		private static <T> void check(Set<ConstraintViolation<T>> violations) {
			if (violations.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (ConstraintViolation<T> violation : violations) {
					sb.append(violation.getMessage());
				}
				throw new IllegalArgumentException(sb.toString());
			}
		}

		public Foo build() {
			validate(this);
			Foo instance = new Foo(this);
			validate(instance);
			return instance;
		}

		public Builder withFoo(String foo) {
			this.foo = foo;
			return this;
		}

		public Builder withWeight(int weight) {
			this.weight = weight;
			return this;
		}

		public String getFoo() {
			return foo;
		}

		public void setFoo(String foo) {
			this.foo = foo;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}
	}

	public String getFoo() {
		return foo;
	}

	public int getWeight() {
		return weight;
	}

}
