package org.kuali.common.util.bind.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.SortedSet;

import org.kuali.common.util.CollectionUtils;

import com.google.common.collect.ImmutableSortedSet;

public final class FieldKeys {

	private final Field field;
	private final ImmutableSortedSet<String> keys;

	private FieldKeys(Builder builder) {
		this.field = builder.field;
		this.keys = ImmutableSortedSet.copyOf(builder.keys);
	}

	public static FieldKeys create(Field field) {
		return builder(field).build();
	}

	public static Builder builder(Field field) {
		return new Builder(field);
	}

	public static class Builder {

		// Required
		private final Field field;

		// Defaults to field.getName()
		private SortedSet<String> keys;

		public Builder(Field field) {
			this.field = field;
			key(field.getName());
		}

		public Builder key(String key) {
			return keys(ImmutableSortedSet.of(key));
		}

		public Builder keys(SortedSet<String> keys) {
			this.keys = keys;
			return this;
		}

		public FieldKeys build() {
			FieldKeys instance = new FieldKeys(this);
			validate(instance);
			return instance;
		}

		private static void validate(FieldKeys instance) {
			checkNotNull(instance.field, "'field' cannot be null");
			checkNotNull(instance.keys, "'keys' cannot be null");
			checkArgument(instance.keys.size() > 0, "'keys' must contain at least one value");
			int blanks = CollectionUtils.getBlanks(instance.keys).size();
			checkArgument(blanks == 0, "'keys' contains %s blanks", blanks);
		}

		public SortedSet<String> getKeys() {
			return keys;
		}

		public void setKeys(SortedSet<String> mappings) {
			this.keys = mappings;
		}

		public Field getField() {
			return field;
		}
	}

	public Field getField() {
		return field;
	}

	public ImmutableSortedSet<String> getKeys() {
		return keys;
	}

}
