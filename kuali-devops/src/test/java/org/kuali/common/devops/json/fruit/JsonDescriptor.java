package org.kuali.common.devops.json.fruit;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;
import org.springframework.core.convert.TypeDescriptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Optional;

@IdiotProofImmutable
public final class JsonDescriptor {

	private final JsonNode node;
	private final Class<?> type;
	private final Optional<TypeDescriptor> descriptor;

	private JsonDescriptor(Builder builder) {
		this.node = builder.node;
		this.descriptor = builder.descriptor;
		this.type = builder.type;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<JsonDescriptor> {

		private JsonNode node;
		private Class<?> type;
		private Optional<TypeDescriptor> descriptor;

		@Override
		public JsonDescriptor build() {
			return validate(make());
		}

		@Override
		public Set<ConstraintViolation<JsonDescriptor>> violations() {
			return violations(make());
		}

		private JsonDescriptor make() {
			return new JsonDescriptor(this);
		}

		public Builder type(Class<?> type) {
			this.type = type;
			return this;
		}

		public Builder node(JsonNode node) {
			this.node = node;
			return this;
		}

		public Builder descriptor(Optional<TypeDescriptor> descriptor) {
			this.descriptor = descriptor;
			return this;
		}

		public Builder descriptor(TypeDescriptor descriptor) {
			return descriptor(Optional.of(descriptor));
		}

		public JsonNode getNode() {
			return node;
		}

		public void setNode(JsonNode node) {
			this.node = node;
		}

		public Class<?> getType() {
			return type;
		}

		public void setType(Class<?> type) {
			this.type = type;
		}

		public Optional<TypeDescriptor> getDescriptor() {
			return descriptor;
		}

		public void setDescriptor(Optional<TypeDescriptor> descriptor) {
			this.descriptor = descriptor;
		}

	}

	public JsonNode getNode() {
		return node;
	}

	public Class<?> getType() {
		return type;
	}

	public Optional<TypeDescriptor> getDescriptor() {
		return descriptor;
	}

}
