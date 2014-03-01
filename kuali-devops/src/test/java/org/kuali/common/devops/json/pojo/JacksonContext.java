package org.kuali.common.devops.json.pojo;

import static com.fasterxml.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;
import static com.fasterxml.jackson.databind.SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * Default context turns on pretty printing, sorts properties alphabetically, and sorts maps based on their keys
 */
@IdiotProofImmutable
public final class JacksonContext {

	private final ObjectMapper mapper;
	private final boolean prettyPrint;
	private final ImmutableList<Module> modules;
	private final ImmutableList<SerializeFeatureContext> serializeFeatures;
	private final ImmutableList<DeserializeFeatureContext> deserializeFeatures;
	private final ImmutableList<MapperFeatureContext> mapperFeatures;

	private JacksonContext(Builder builder) {
		this.mapper = builder.mapper;
		this.prettyPrint = builder.prettyPrint;
		this.modules = ImmutableList.copyOf(builder.modules);
		this.serializeFeatures = ImmutableList.copyOf(builder.serializeFeatures);
		this.deserializeFeatures = ImmutableList.copyOf(builder.deserializeFeatures);
		this.mapperFeatures = ImmutableList.copyOf(builder.mapperFeatures);

		// Register any modules they've provided
		for (Module module : modules) {
			this.mapper.registerModule(module);
		}
		// Register any serialize features they've provided
		for (SerializeFeatureContext ctx : serializeFeatures) {
			this.mapper.configure(ctx.getFeature(), ctx.isEnabled());
		}
		// Register any deserialize features they've provided
		for (DeserializeFeatureContext ctx : deserializeFeatures) {
			this.mapper.configure(ctx.getFeature(), ctx.isEnabled());
		}
		// Register any mapper features they've provided
		for (MapperFeatureContext ctx : mapperFeatures) {
			this.mapper.configure(ctx.getFeature(), ctx.isEnabled());
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
		private List<Module> modules = Lists.<Module> newArrayList(new GuavaModule());
		private ObjectMapper mapper = new ObjectMapper();
		private List<SerializeFeatureContext> serializeFeatures = newArrayList(new SerializeFeatureContext(ORDER_MAP_ENTRIES_BY_KEYS, true));
		private List<MapperFeatureContext> mapperFeatures = newArrayList(new MapperFeatureContext(SORT_PROPERTIES_ALPHABETICALLY, true));
		private List<DeserializeFeatureContext> deserializeFeatures = newArrayList();

		@Override
		public JacksonContext build() {
			return validate(new JacksonContext(this));
		}

		public Builder withMapperFeatures(List<MapperFeatureContext> mapperFeatures) {
			this.mapperFeatures = mapperFeatures;
			return this;
		}

		public Builder withFeature(MapperFeature feature, boolean enabled) {
			return withMapperFeatures(newArrayList(new MapperFeatureContext(feature, enabled)));
		}

		public Builder addFeature(MapperFeature feature, boolean enabled) {
			this.mapperFeatures.add(new MapperFeatureContext(feature, enabled));
			return this;
		}

		public Builder withDeserializeFeatures(List<DeserializeFeatureContext> deserializeFeatures) {
			this.deserializeFeatures = deserializeFeatures;
			return this;
		}

		public Builder withFeature(DeserializationFeature feature, boolean enabled) {
			return withDeserializeFeatures(newArrayList(new DeserializeFeatureContext(feature, enabled)));
		}

		public Builder addFeature(DeserializationFeature feature, boolean enabled) {
			this.deserializeFeatures.add(new DeserializeFeatureContext(feature, enabled));
			return this;
		}

		public Builder withSerializeFeatures(List<SerializeFeatureContext> serializeFeatures) {
			this.serializeFeatures = serializeFeatures;
			return this;
		}

		public Builder withFeature(SerializationFeature feature, boolean enabled) {
			return withSerializeFeatures(newArrayList(new SerializeFeatureContext(feature, enabled)));
		}

		public Builder addFeature(SerializationFeature feature, boolean enabled) {
			this.serializeFeatures.add(new SerializeFeatureContext(feature, enabled));
			return this;
		}

		public Builder withMapper(ObjectMapper mapper) {
			this.mapper = mapper;
			return this;
		}

		public Builder withModules(List<Module> modules) {
			this.modules = modules;
			return this;
		}

		public Builder addModule(Module module) {
			this.modules.add(module);
			return this;
		}

		public Builder withModule(Module module) {
			return withModules(newArrayList(module));
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

		public List<Module> getModules() {
			return modules;
		}

		public void setModules(List<Module> modules) {
			this.modules = modules;
		}

		public List<SerializeFeatureContext> getSerializeFeatures() {
			return serializeFeatures;
		}

		public void setSerializeFeatures(List<SerializeFeatureContext> serializeFeatures) {
			this.serializeFeatures = serializeFeatures;
		}

		public List<MapperFeatureContext> getMapperFeatures() {
			return mapperFeatures;
		}

		public void setMapperFeatures(List<MapperFeatureContext> mapperFeatures) {
			this.mapperFeatures = mapperFeatures;
		}

		public List<DeserializeFeatureContext> getDeserializeFeatures() {
			return deserializeFeatures;
		}

		public void setDeserializeFeatures(List<DeserializeFeatureContext> deserializeFeatures) {
			this.deserializeFeatures = deserializeFeatures;
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

	public List<SerializeFeatureContext> getSerializeFeatures() {
		return serializeFeatures;
	}

	public List<DeserializeFeatureContext> getDeserializeFeatures() {
		return deserializeFeatures;
	}

	public List<MapperFeatureContext> getMapperFeatures() {
		return mapperFeatures;
	}

}
