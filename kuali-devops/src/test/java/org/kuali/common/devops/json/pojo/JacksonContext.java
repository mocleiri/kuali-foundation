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
	private final ImmutableList<SerializeContext> serializeFeatures;
	private final ImmutableList<DeserializeContext> deserializeFeatures;
	private final ImmutableList<MapperContext> mapperFeatures;
	private final ImmutableList<MixInContext> mixins;

	private JacksonContext(Builder builder) {
		this.mapper = builder.mapper;
		this.prettyPrint = builder.prettyPrint;
		this.modules = ImmutableList.copyOf(builder.modules);
		this.serializeFeatures = ImmutableList.copyOf(builder.serializeFeatures);
		this.deserializeFeatures = ImmutableList.copyOf(builder.deserializeFeatures);
		this.mapperFeatures = ImmutableList.copyOf(builder.mapperFeatures);
		this.mixins = ImmutableList.copyOf(builder.mixins);

		// Register any modules they've provided
		for (Module module : modules) {
			this.mapper.registerModule(module);
		}

		// Register any serialize features they've provided
		for (SerializeContext ctx : serializeFeatures) {
			this.mapper.configure(ctx.getFeature(), ctx.isEnabled());
		}

		// Register any deserialize features they've provided
		for (DeserializeContext ctx : deserializeFeatures) {
			this.mapper.configure(ctx.getFeature(), ctx.isEnabled());
		}

		// Register any mapper features they've provided
		for (MapperContext ctx : mapperFeatures) {
			this.mapper.configure(ctx.getFeature(), ctx.isEnabled());
		}

		// Register any mixin's they've provided
		for (MixInContext mixin : mixins) {
			this.mapper.addMixInAnnotations(mixin.getTarget(), mixin.getSource());
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
		private List<SerializeContext> serializeFeatures = newArrayList(new SerializeContext(ORDER_MAP_ENTRIES_BY_KEYS, true));
		private List<MapperContext> mapperFeatures = newArrayList(new MapperContext(SORT_PROPERTIES_ALPHABETICALLY, true));
		private List<DeserializeContext> deserializeFeatures = newArrayList();
		private List<MixInContext> mixins = newArrayList();

		@Override
		public JacksonContext build() {
			return validate(new JacksonContext(this));
		}

		public Builder withMixins(List<MixInContext> mixins) {
			this.mixins = mixins;
			return this;
		}

		public Builder addMixins(MixInContext mixin) {
			this.mixins.add(mixin);
			return this;
		}

		public Builder withMapperFeatures(List<MapperContext> mapperFeatures) {
			this.mapperFeatures = mapperFeatures;
			return this;
		}

		public Builder withFeature(MapperFeature feature, boolean enabled) {
			return withMapperFeatures(newArrayList(new MapperContext(feature, enabled)));
		}

		public Builder addFeature(MapperFeature feature, boolean enabled) {
			this.mapperFeatures.add(new MapperContext(feature, enabled));
			return this;
		}

		public Builder withDeserializeFeatures(List<DeserializeContext> deserializeFeatures) {
			this.deserializeFeatures = deserializeFeatures;
			return this;
		}

		public Builder withFeature(DeserializationFeature feature, boolean enabled) {
			return withDeserializeFeatures(newArrayList(new DeserializeContext(feature, enabled)));
		}

		public Builder addFeature(DeserializationFeature feature, boolean enabled) {
			this.deserializeFeatures.add(new DeserializeContext(feature, enabled));
			return this;
		}

		public Builder withSerializeFeatures(List<SerializeContext> serializeFeatures) {
			this.serializeFeatures = serializeFeatures;
			return this;
		}

		public Builder withFeature(SerializationFeature feature, boolean enabled) {
			return withSerializeFeatures(newArrayList(new SerializeContext(feature, enabled)));
		}

		public Builder addFeature(SerializationFeature feature, boolean enabled) {
			this.serializeFeatures.add(new SerializeContext(feature, enabled));
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

		public List<SerializeContext> getSerializeFeatures() {
			return serializeFeatures;
		}

		public void setSerializeFeatures(List<SerializeContext> serializeFeatures) {
			this.serializeFeatures = serializeFeatures;
		}

		public List<MapperContext> getMapperFeatures() {
			return mapperFeatures;
		}

		public void setMapperFeatures(List<MapperContext> mapperFeatures) {
			this.mapperFeatures = mapperFeatures;
		}

		public List<DeserializeContext> getDeserializeFeatures() {
			return deserializeFeatures;
		}

		public void setDeserializeFeatures(List<DeserializeContext> deserializeFeatures) {
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

	public List<SerializeContext> getSerializeFeatures() {
		return serializeFeatures;
	}

	public List<DeserializeContext> getDeserializeFeatures() {
		return deserializeFeatures;
	}

	public List<MapperContext> getMapperFeatures() {
		return mapperFeatures;
	}

}
