package org.kuali.common.util.validate.hibernate.programmatic;

import java.lang.annotation.ElementType;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.defs.NotNullDef;
import org.kuali.common.util.validate.NoNullFields;
import org.kuali.common.util.validate.ValidationUtils;

@NoNullFields
public class A {

	private final String foo;

	private A(Builder builder) {
		this.foo = builder.foo;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private String foo;

		private void validate(Builder builder) {
			HibernateValidatorConfiguration configuration = Validation.byProvider(HibernateValidator.class).configure();
			ConstraintMapping cm = configuration.createConstraintMapping();
			cm.type(Builder.class).property("foo", ElementType.FIELD).constraint(new NotNullDef());
			Validator validator = configuration.addMapping(cm).buildValidatorFactory().getValidator();
			check(validator.validate(builder));
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

		public String getFoo() {
			return foo;
		}

		public void setFoo(String foo) {
			this.foo = foo;
		}
	}

	public String getFoo() {
		return foo;
	}

}
