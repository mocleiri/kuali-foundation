package org.kuali.common.util.bind.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.bind.api.BindMapping;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class BoundField {

	private final Field field;
	private final ImmutableList<String> keys;
	private final Optional<BindMapping> mapping;

	public static BoundField create(Field field) {
		return builder(field).build();
	}

	public static Builder builder(Field field) {
		return new Builder(field);
	}

	private BoundField(Builder builder) {
		this.field = builder.field;
		this.keys = ImmutableList.copyOf(builder.keys);
		this.mapping = builder.mapping;
	}

	public static class Builder {

		// Required
		private final Field field;

		private List<String> keys;
		private Optional<BindMapping> mapping;

		public Builder(Field field) {
			this.field = field;
			key(field.getName());
		}

		public Builder key(String key) {
			return keys(ImmutableList.of(key));
		}

		public Builder keys(List<String> keys) {
			this.keys = keys;
			return this;
		}

		public Builder mapping(BindMapping mapping) {
			return mapping(Optional.of(mapping));
		}

		public Builder mapping(Optional<BindMapping> mapping) {
			this.mapping = mapping;
			return this;
		}

		public BoundField build() {
			BoundField instance = new BoundField(this);
			validate(instance);
			return instance;
		}

		private static void validate(BoundField instance) {
			checkNotNull(instance.field, "'field' cannot be null");
			checkNotNull(instance.mapping, "'mapping' cannot be null");
			checkNotNull(instance.keys, "'keys' cannot be null");
			checkArgument(instance.keys.size() > 0, "'keys' must contain at least one value");
			int blanks = CollectionUtils.getBlanks(instance.keys).size();
			checkArgument(blanks == 0, "'keys' contains %s blanks", blanks);
		}

		public List<String> getKeys() {
			return keys;
		}

		public void setKeys(List<String> keys) {
			this.keys = keys;
		}

		public Field getField() {
			return field;
		}

		public Optional<BindMapping> getMapping() {
			return mapping;
		}

		public void setMapping(Optional<BindMapping> mapping) {
			this.mapping = mapping;
		}

	}

	public Field getField() {
		return field;
	}

	public ImmutableList<String> getKeys() {
		return keys;
	}

	public Optional<BindMapping> getMapping() {
		return mapping;
	}

}
