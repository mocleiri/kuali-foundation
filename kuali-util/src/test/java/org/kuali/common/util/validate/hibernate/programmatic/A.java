package org.kuali.common.util.validate.hibernate.programmatic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Min;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.defs.MinDef;
import org.kuali.common.util.validate.MatchDeclaringClassFields;
import org.kuali.common.util.validate.NoNullFields;
import org.kuali.common.util.validate.NoNullFieldsDef;
import org.kuali.common.util.validate.ValidationUtils;

@NoNullFields
public class A {

	@Min(0)
	private final int weight;
	private final String foo;

	private A(Builder builder) {
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
				HibernateValidatorConfiguration configuration = Validation.byProvider(HibernateValidator.class).configure();
				ConstraintMapping cm = configuration.createConstraintMapping();
				Field field = A.class.getDeclaredField("weight");
				Annotation[] annotations = field.getAnnotations();
				for (Annotation a:annotations) {
					Class<?> type = a.annotationType();
					Annotation[] aa = type.getAnnotations();
				}
				Min annotation = field.getAnnotation(Min.class);
				MinDef md = new MinDef().value(annotation.value());
				cm.type(Builder.class).constraint(new NoNullFieldsDef());
				Validator validator = configuration.addMapping(cm).buildValidatorFactory().getValidator();
				check(validator.validate(builder));
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}

		private void validate(A instance) {
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

		public A build() {
			validate(this);
			A instance = new A(this);
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
