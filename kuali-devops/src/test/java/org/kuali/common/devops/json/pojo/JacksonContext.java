package org.kuali.common.devops.json.pojo;

import static com.fasterxml.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;
import static com.fasterxml.jackson.databind.SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

/**
 * Default context turns on pretty printing, sorts properties alphabetically, and sorts maps based on their keys
 */
@IdiotProofImmutable
public final class JacksonContext {

	private final ObjectMapper mapper;
	private final boolean prettyPrint;

	private JacksonContext(Builder builder) {
		this.mapper = builder.mapper;
		this.prettyPrint = builder.prettyPrint;
	}

	public static JacksonContext newJacksonJsonContext() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	/**
	 * ObjectMapper with {@code GuavaModule} registered, sorts properties alphabetically, and sorts map entries by their keys.
	 */
	public static ObjectMapper getNewDefaultObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new GuavaModule());
		mapper.configure(SORT_PROPERTIES_ALPHABETICALLY, true);
		mapper.configure(ORDER_MAP_ENTRIES_BY_KEYS, true);
		return mapper;
	}

	public static class Builder extends ValidatingBuilder<JacksonContext> {

		private boolean prettyPrint = true;
		private ObjectMapper mapper = getNewDefaultObjectMapper();

		@Override
		public JacksonContext build() {
			return validate(new JacksonContext(this));
		}

		public Builder withPrettyPrint(boolean prettyPrint) {
			this.prettyPrint = prettyPrint;
			return this;
		}

		public Builder withMapper(ObjectMapper mapper) {
			this.mapper = mapper;
			return this;
		}
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public boolean isPrettyPrint() {
		return prettyPrint;
	}
}
