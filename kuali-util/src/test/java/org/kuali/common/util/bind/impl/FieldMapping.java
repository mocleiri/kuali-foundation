package org.kuali.common.util.bind.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.SortedSet;

import org.kuali.common.util.bind.model.FieldKeys;

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Sets;

public final class FieldMapping {

	private final Field field;
	private final ImmutableSortedSet<String> mappings;

	private FieldMapping(Builder builder) {
		this.field = builder.field;
		this.mappings = ImmutableSortedSet.copyOf(builder.mappings);
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
		private SortedSet<String> mappings = Sets.newTreeSet();

		public Builder(Field field) {
			this.field = field;
		}

		public Builder mappings(SortedSet<String> mappings) {
			this.mappings = mappings;
			return this;
		}

		public FieldKeys build() {
			FieldKeys instance = new FieldKeys(this);
			validate(instance);
			return instance;
		}

		private static void validate(FieldKeys instance) {
			checkNotNull(instance.field, "'field' cannot be null");
			checkNotNull(instance.mappings, "'mappings' cannot be null");
		}

		public SortedSet<String> getMappings() {
			return mappings;
		}

		public void setMappings(SortedSet<String> mappings) {
			this.mappings = mappings;
		}

		public Field getField() {
			return field;
		}
	}

	public Field getField() {
		return field;
	}

	public ImmutableSortedSet<String> getMappings() {
		return mappings;
	}

}
