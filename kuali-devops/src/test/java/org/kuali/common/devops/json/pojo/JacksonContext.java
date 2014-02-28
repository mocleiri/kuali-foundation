package org.kuali.common.devops.json.pojo;

import static com.fasterxml.jackson.databind.SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS;

import java.util.List;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class JacksonContext {

	private final ObjectMapper mapper;
	private final boolean prettyPrint;
	private final boolean orderMapEntriesByKeys;
	private final ImmutableList<Module> modules;

	private JacksonContext(Builder builder) {
		this.mapper = builder.mapper;
		this.prettyPrint = builder.prettyPrint;
		this.orderMapEntriesByKeys = builder.orderMapEntriesByKeys;
		this.modules = ImmutableList.copyOf(builder.modules);

		// Register any modules they've provided
		for (Module module : modules) {
			this.mapper.registerModule(module);
		}

		this.mapper.configure(ORDER_MAP_ENTRIES_BY_KEYS, orderMapEntriesByKeys);
	}

	public static JacksonContext newJacksonJsonContext() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<JacksonContext> {

		private boolean prettyPrint = true;
		private List<Module> modules = ImmutableList.<Module> of(new GuavaModule());
		private ObjectMapper mapper = new ObjectMapper();
		private boolean orderMapEntriesByKeys;

		@Override
		public JacksonContext build() {
			return validate(make());
		}

		private JacksonContext make() {
			return new JacksonContext(this);
		}

		public Builder withOrderMapEntriesByKeys(boolean orderMapEntriesByKeys) {
			this.orderMapEntriesByKeys = orderMapEntriesByKeys;
			return this;
		}

		public Builder withMapper(ObjectMapper mapper) {
			this.mapper = mapper;
			return this;
		}

		public Builder withModule(Module module) {
			return withModules(ImmutableList.of(module));
		}

		public Builder withModules(List<Module> modules) {
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
