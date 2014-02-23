package org.kuali.common.devops.json.fruit;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;
import org.springframework.core.convert.TypeDescriptor;

import com.fasterxml.jackson.databind.JsonNode;

@IdiotProofImmutable
public final class JsonDescriptor {

	private final JsonNode node;
	private final TypeDescriptor descriptor;

	private JsonDescriptor(Builder builder) {
		this.node = builder.node;
		this.descriptor = builder.descriptor;
	}

	public static class Builder extends ValidatingBuilder<JsonDescriptor> {

		private JsonNode node;
		private TypeDescriptor descriptor;

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

		public Builder node(JsonNode node) {
			this.node = node;
			return this;
		}

		public Builder descriptor(TypeDescriptor descriptor) {
			this.descriptor = descriptor;
			return this;
		}

		public JsonNode getNode() {
			return node;
		}

		public void setNode(JsonNode node) {
			this.node = node;
		}

		public TypeDescriptor getDescriptor() {
			return descriptor;
		}

		public void setDescriptor(TypeDescriptor descriptor) {
			this.descriptor = descriptor;
		}

	}

	public JsonNode getNode() {
		return node;
	}

	public TypeDescriptor getDescriptor() {
		return descriptor;
	}

}
