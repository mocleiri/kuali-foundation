package org.kuali.common.util.bind.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Optional;

public final class BoundType {

	private final Optional<String> prefix;
	private final Class<?> type;
	private final FieldKeys fieldKeys;

	private BoundType(Builder builder) {
		this.prefix = builder.prefix;
		this.type = builder.type;
		this.fieldKeys = builder.fieldKeys;
	}

	public static class Builder {

		private Class<?> type;
		private Optional<String> prefix;
		private FieldKeys fieldKeys;

		public Builder prefix(String prefix) {
			this.prefix = Optional.of(prefix);
			return this;
		}

		public Builder type(Class<?> type) {
			this.type = type;
			return this;
		}

		public Builder fieldKeys(FieldKeys fieldKeys) {
			this.fieldKeys = fieldKeys;
			return this;
		}

		public BoundType build() {
			BoundType instance = new BoundType(this);
			validate(instance);
			return instance;
		}

		private static void validate(BoundType instance) {
			checkNotNull(instance.type, "'type' cannot be null");
			checkNotNull(instance.fieldKeys, "'fieldKeys' cannot be null");
			checkNotNull(instance.prefix, "'prefix' cannot be null");
			if (instance.prefix.isPresent()) {
				checkArgument(!StringUtils.isBlank(instance.prefix.get()), "'prefix' cannot be blank");
			}
		}

		public Class<?> getType() {
			return type;
		}

		public void setType(Class<?> type) {
			this.type = type;
		}

		public Optional<String> getPrefix() {
			return prefix;
		}

		public void setPrefix(Optional<String> prefix) {
			this.prefix = prefix;
		}

		public FieldKeys getFieldKeys() {
			return fieldKeys;
		}

		public void setFieldKeys(FieldKeys fieldKeys) {
			this.fieldKeys = fieldKeys;
		}
	}

	public Optional<String> getPrefix() {
		return prefix;
	}

	public Class<?> getType() {
		return type;
	}

	public FieldKeys getFieldKeys() {
		return fieldKeys;
	}

}
