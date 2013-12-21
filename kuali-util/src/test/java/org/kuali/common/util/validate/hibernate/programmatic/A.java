package org.kuali.common.util.validate.hibernate.programmatic;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Min;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.GenericConstraintDef;
import org.kuali.common.util.validate.MatchDeclaringClassFields;
import org.kuali.common.util.validate.NoNullFields;
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
				Annotation[] annotations = builder.getClass().getDeclaringClass().getAnnotations();
				for (Annotation annotation : annotations) {
					Class<?> type = annotation.annotationType();
					if (type == NoNullFields.class) {
						ConstraintMapping cm = configuration.createConstraintMapping();
						NoNullFields nnf = builder.getClass().getAnnotation(NoNullFields.class);
						GenericConstraintDef<NoNullFields> gcf = new GenericConstraintDef<NoNullFields>(NoNullFields.class);
						cm.type(builder.getClass()).constraint(gcf);
						configuration = configuration.addMapping(cm);
					}
				}
				Validator validator = configuration.buildValidatorFactory().getValidator();
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
