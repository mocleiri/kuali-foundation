package org.kuali.common.util.bind.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.SortedSet;

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Sets;

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

		// Optional
		private SortedSet<String> keys = Sets.newTreeSet();

		public Builder(Field field) {
			this.field = field;
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
			checkNotNull(instance.keys, "'mappings' cannot be null");
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
