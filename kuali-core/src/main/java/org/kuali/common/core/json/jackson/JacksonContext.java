package org.kuali.common.core.json.jackson;

import static com.fasterxml.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;
import static com.fasterxml.jackson.databind.SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

/**
 * Provides context for JacksonJsonService
 */
@IdiotProofImmutable
public final class JacksonContext {

	private final ObjectMapper mapper;
	private final boolean prettyPrint;
	private final boolean copyObjectMapper;

	private JacksonContext(Builder builder) {
		this.mapper = builder.mapper;
		this.prettyPrint = builder.prettyPrint;
		this.copyObjectMapper = builder.copyObjectMapper;
	}

	public static JacksonContext newJacksonContext() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Produces a new ObjectMapper with these characteristics:
	 * 
	 * <ul>
	 * <li>registered GuavaModule</li>
	 * <li>registered CanonicalFileModule</li>
	 * <li>sorts properties alphabetically when serializing</li>
	 * <li>sorts map entries based on their keys when serializing</li>
	 * <li>turns on pretty printing when serializing</li>
	 * </ul>
	 */
	public static ObjectMapper newDefaultObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new GuavaModule());
		mapper.registerModule(new CanonicalFileModule());
		mapper.configure(SORT_PROPERTIES_ALPHABETICALLY, true);
		mapper.configure(ORDER_MAP_ENTRIES_BY_KEYS, true);
		return mapper;
	}

	public static class Builder extends ValidatingBuilder<JacksonContext> {

		private boolean prettyPrint = true;
		private ObjectMapper mapper = newDefaultObjectMapper();
		private boolean copyObjectMapper = true;

		@Override
		public JacksonContext build() {
			return validate(new JacksonContext(this));
		}

		public Builder withCopyObjectMapper(boolean copyObjectMapper) {
			this.copyObjectMapper = copyObjectMapper;
			return this;
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

	public boolean isCopyObjectMapper() {
		return copyObjectMapper;
	}
}
