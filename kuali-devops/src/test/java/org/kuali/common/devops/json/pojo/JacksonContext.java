package org.kuali.common.devops.json.pojo;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public class JacksonContext {

	private final ObjectMapper mapper;
	private final boolean prettyPrint;
	private final List<Module> modules;

	private JacksonContext(Builder builder) {
		this.mapper = builder.mapper;
		this.prettyPrint = builder.prettyPrint;
		this.modules = builder.modules;

		// Register any modules they've provided
		for (Module module : this.modules) {
			this.mapper.registerModule(module);
		}
	}

	@JsonCreator
	private static JacksonContext jsonCreator(@JsonProperty("mapper") ObjectMapper mapper, @JsonProperty("prettyPrint") boolean prettyPrint,
			@JsonProperty("modules") List<Module> modules) {
		return builder().mapper(mapper).prettyPrint(prettyPrint).modules(modules).build();
	}

	public static JacksonContext newJacksonJsonContext() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<JacksonContext> {

		private boolean prettyPrint = false;
		private List<Module> modules = ImmutableList.<Module> of(new GuavaModule());
		private ObjectMapper mapper = new ObjectMapper();

		@Override
		public JacksonContext build() {
			return validate(make());
		}

		@Override
		public Set<ConstraintViolation<JacksonContext>> violations() {
			return violations(make());
		}

		private JacksonContext make() {
			return new JacksonContext(this);
		}

		public Builder mapper(ObjectMapper mapper) {
			this.mapper = mapper;
			return this;
		}

		public Builder modules(List<Module> modules) {
			this.modules = modules;
			return this;
		}

		public Builder prettyPrint(boolean prettyPrint) {
			this.prettyPrint = prettyPrint;
			return this;
		}

		public ObjectMapper getMapper() {
			return mapper;
		}

		public void setMapper(ObjectMapper mapper) {
			this.mapper = mapper;
		}

		public boolean isPrettyPrint() {
			return prettyPrint;
		}

		public void setPrettyPrint(boolean prettyPrint) {
			this.prettyPrint = prettyPrint;
		}

	}

	public boolean isPrettyPrint() {
		return prettyPrint;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public List<Module> getModules() {
		return modules;
	}

}
