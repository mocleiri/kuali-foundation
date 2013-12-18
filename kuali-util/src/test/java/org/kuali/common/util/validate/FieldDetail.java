package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public final class FieldDetail {

	private final Class<?> type;
	private final Set<Field> set;
	private final Map<String, Field> map;

	private FieldDetail(Builder builder) {
		this.type = builder.type;
		this.set = builder.set;
		this.map = builder.map;
	}

	public static Builder builder(Class<?> type) {
		return new Builder(type);
	}

	public static class Builder implements org.kuali.common.util.builder.Builder<FieldDetail> {

		private final Class<?> type;
		private Set<Field> set = ImmutableSet.of();
		private Map<String, Field> map = ImmutableMap.of();

		public Builder(Class<?> type) {
			this.type = type;
		}

		public Builder withSet(Set<Field> set) {
			this.set = set;
			return this;
		}

		public Builder withMap(Map<String, Field> map) {
			this.map = map;
			return this;
		}

		@Override
		public FieldDetail build() {
			this.set = ImmutableSet.copyOf(set);
			this.map = ImmutableMap.copyOf(map);
			FieldDetail instance = new FieldDetail(this);
			validate(instance);
			return instance;
		}

		private void validate(FieldDetail instance) {
			Preconditions.checkNotNull(instance.type, "type cannot be null");
			Preconditions.checkNotNull(instance.set, "set cannot be null");
			Preconditions.checkNotNull(instance.map, "map cannot be null");
		}
	}

	public Class<?> getType() {
		return type;
	}

	public Set<Field> getSet() {
		return set;
	}

	public Map<String, Field> getMap() {
		return map;
	}

}
