package org.kuali.common.util.validate.hibernate.programmatic;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintDef;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.validate.BulletProofBuilder;
import org.kuali.common.util.validate.BulletProofPojo;
import org.kuali.common.util.validate.ValidationUtils;
import org.kuali.common.util.validate.hibernate.factory.ConstraintDefService;
import org.kuali.common.util.validate.hibernate.factory.DefaultConstraintDefService;

@BulletProofPojo
public class Foo {

	@Size(min = 12)
	private final String foo;

	@Min(1)
	private final int weight;

	private Foo(Builder builder) {
		this.foo = builder.foo;
		this.weight = builder.weight;
	}

	public static Builder builder() {
		return new Builder();
	}

	@BulletProofBuilder
	public static class Builder {

		private String foo;
		private int weight;

		private void validate(Builder builder) {
			HibernateValidatorConfiguration configuration = Validation.byProvider(HibernateValidator.class).configure();
			copyClassConstraints(builder.getClass().getDeclaringClass(), builder.getClass(), configuration);
			copyFieldConstraints(builder.getClass().getDeclaringClass(), builder.getClass(), configuration);
			Validator validator = configuration.buildValidatorFactory().getValidator();
			Set<ConstraintViolation<Builder>> violations = validator.validate(builder);
			ValidationUtils.check(violations);
		}

		private static void copyClassConstraints(Class<?> src, Class<?> dst, HibernateValidatorConfiguration configuration) {
			ConstraintDefService cdf = DefaultConstraintDefService.builder().build();
			List<Annotation> annotations = ValidationUtils.getConstraints(src);
			for (Annotation annotation : annotations) {
				ConstraintDef<?, ?> cdef = cdf.getConstraintDef(src, annotation.annotationType());
				ConstraintMapping cm = configuration.createConstraintMapping();
				cm.type(dst).constraint(cdef);
				configuration.addMapping(cm);
			}
		}

		private static void copyFieldConstraints(Class<?> src, Class<?> dst, HibernateValidatorConfiguration configuration) {
			ConstraintDefService cdf = DefaultConstraintDefService.builder().build();
			Set<Field> fields = ReflectionUtils.getAllFields(src);
			for (Field field : fields) {
				List<Annotation> annotations = ValidationUtils.getConstraints(field);
				for (Annotation annotation : annotations) {
					ConstraintDef<?, ?> cdef = cdf.getConstraintDef(field, annotation.annotationType());
					ConstraintMapping cm = configuration.createConstraintMapping();
					cm.type(dst).property(field.getName(), ElementType.FIELD).constraint(cdef);
					configuration.addMapping(cm);
				}
			}
		}

		private void validate(Foo instance) {
			Validator validator = ValidationUtils.getDefaultValidator();
			Set<ConstraintViolation<Foo>> violations = validator.validate(instance);
			ValidationUtils.check(violations);
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
