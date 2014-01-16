package org.kuali.common.util.bind.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public final class BoundType {

	private final Optional<String> prefix;
	private final Class<?> type;
	private final ImmutableMap<Field, BoundField> fields;

	private BoundType(Builder builder) {
		this.prefix = builder.prefix;
		this.type = builder.type;
		this.fields = ImmutableMap.copyOf(builder.fields);
	}

	public static Builder builder(Class<?> type) {
		return new Builder(type);
	}

	public static class Builder {

		// Required
		private final Class<?> type;

		// Optional
		private Optional<String> prefix = Optional.absent();
		private Map<Field, BoundField> fields = Maps.newHashMap();

		private Builder(Class<?> type) {
			this.type = type;
		}

		public Builder prefix(String prefix) {
			this.prefix = Optional.of(prefix);
			return this;
		}

		public Builder fields(Map<Field, BoundField> fields) {
			this.fields = fields;
			return this;
		}

		public BoundType build() {
			BoundType instance = new BoundType(this);
			validate(instance);
			return instance;
		}

		private static void validate(BoundType instance) {
			checkNotNull(instance.type, "'type' cannot be null");
			checkNotNull(instance.fields, "'fields' cannot be null");
			checkNotNull(instance.prefix, "'prefix' cannot be null");
			if (instance.prefix.isPresent()) {
				checkArgument(!StringUtils.isBlank(instance.prefix.get()), "'prefix' cannot be blank");
			}
		}

		public Class<?> getType() {
			return type;
		}

		public Optional<String> getPrefix() {
			return prefix;
		}

		public void setPrefix(Optional<String> prefix) {
			this.prefix = prefix;
		}

		public Map<Field, BoundField> getFields() {
			return fields;
		}

		public void setFields(Map<Field, BoundField> fieldKeys) {
			this.fields = fieldKeys;
		}

	}

	public Optional<String> getPrefix() {
		return prefix;
	}

	public Class<?> getType() {
		return type;
	}

	public ImmutableMap<Field, BoundField> getFields() {
		return fields;
	}

}
