package org.kuali.common.devops.json.pojo;

import static com.fasterxml.jackson.databind.SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class JacksonContext {

	private final ObjectMapper mapper;
	private final boolean prettyPrint;
	private final ImmutableList<Module> modules;
	private final ImmutableList<SerializeFeatureContext> serializationFeatures;
	private final ImmutableList<DeserializeFeatureContext> deserializationFeatures;

	private JacksonContext(Builder builder) {
		this.mapper = builder.mapper;
		this.prettyPrint = builder.prettyPrint;
		this.modules = ImmutableList.copyOf(builder.modules);
		this.serializationFeatures = ImmutableList.copyOf(builder.serializationFeatures);
		this.deserializationFeatures = ImmutableList.copyOf(builder.deserializationFeatures);

		// Register any modules they've provided
		for (Module module : modules) {
			this.mapper.registerModule(module);
		}
		for (SerializeFeatureContext sfc : serializationFeatures) {
			this.mapper.configure(sfc.getFeature(), sfc.isState());
		}
		for (DeserializeFeatureContext dfc : deserializationFeatures) {
			this.mapper.configure(dfc.getFeature(), dfc.isState());
		}

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
		private List<SerializeFeatureContext> serializationFeatures = newArrayList(new SerializeFeatureContext(ORDER_MAP_ENTRIES_BY_KEYS, true));
		private List<DeserializeFeatureContext> deserializationFeatures = ImmutableList.of();

		@Override
		public JacksonContext build() {
			return validate(new JacksonContext(this));
		}

		public Builder withSerializationFeatures(List<SerializeFeatureContext> serializationFeatures) {
			this.serializationFeatures = serializationFeatures;
			return this;
		}

		public Builder withSerializationFeature(SerializationFeature feature, boolean state) {
			this.serializationFeatures = serializationFeatures;
			return this;
		}

		public Builder withDeserializationFeatures(List<DeserializeFeatureContext> deserializationFeatures) {
			this.deserializationFeatures = deserializationFeatures;
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
