package org.kuali.common.util.bind.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.ImmutableList.of;
import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.CollectionUtils.getBlanks;

import java.lang.reflect.Field;
import java.util.List;

import org.kuali.common.util.bind.api.BindingAlias;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class BoundFieldDescriptor {

	private final Field field;
	private final ImmutableList<String> keys;
	private final Optional<BindingAlias> mapping;

	public static BoundFieldDescriptor create(Field field) {
		return builder(field).build();
	}

	public static Builder builder(Field field) {
		return new Builder(field);
	}

	private BoundFieldDescriptor(Builder builder) {
		this.field = builder.field;
		this.keys = copyOf(builder.keys);
		this.mapping = builder.mapping;
	}

	public static class Builder implements org.kuali.common.util.build.Builder<BoundFieldDescriptor> {

		// Required
		private final Field field;

		private List<String> keys;
		private Optional<BindingAlias> mapping;

		public Builder(Field field) {
			this.field = field;
			key(field.getName());
		}

		public Builder key(String key) {
			return keys(newArrayList(of(key)));
		}

		public Builder keys(List<String> keys) {
			this.keys = keys;
			return this;
		}

		public Builder mapping(BindingAlias mapping) {
			return mapping(Optional.of(mapping));
		}

		public Builder mapping(Optional<BindingAlias> mapping) {
			this.mapping = mapping;
			return this;
		}

		@Override
		public BoundFieldDescriptor build() {
			BoundFieldDescriptor instance = new BoundFieldDescriptor(this);
			validate(instance);
			return instance;
		}

		private static void validate(BoundFieldDescriptor instance) {
			checkNotNull(instance.field, "'field' cannot be null");
			checkNotNull(instance.mapping, "'mapping' cannot be null");
			checkNotNull(instance.keys, "'keys' cannot be null");
			checkArgument(instance.keys.size() > 0, "'keys' must contain at least one value");
			int blanks = getBlanks(instance.keys).size();
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

		public Optional<BindingAlias> getMapping() {
			return mapping;
		}

		public void setMapping(Optional<BindingAlias> mapping) {
			this.mapping = mapping;
		}

	}

	public Field getField() {
		return field;
	}

	public ImmutableList<String> getKeys() {
		return keys;
	}

	public Optional<BindingAlias> getMapping() {
		return mapping;
	}

}
