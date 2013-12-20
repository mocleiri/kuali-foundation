package org.kuali.common.util.validate.hibernate.programmatic;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

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

		private void validate(A instance) {
			Validator validator = ValidationUtils.getDefaultValidator();
			Set<ConstraintViolation<A>> violations = validator.validate(instance);
			if (violations.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (ConstraintViolation<A> violation : violations) {
					sb.append(violation.getMessage());
				}
				throw new IllegalArgumentException(sb.toString());
			}
		}

		public A build() {
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
