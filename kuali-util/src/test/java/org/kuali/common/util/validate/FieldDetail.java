package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public final class FieldDetail {

	private final Class<?> type;
	private final ImmutableSet<Field> set;
	private final ImmutableMap<String, Field> map;

	private FieldDetail(Builder builder) {
		this.type = builder.type;
		this.set = ImmutableSet.copyOf(builder.set);
		this.map = ImmutableMap.copyOf(builder.map);
	}

	public static Builder builder(Class<?> type) {
		return new Builder(type);
	}

	public static class Builder implements org.kuali.common.util.build.Builder<FieldDetail> {

		private final Class<?> type;
		private Set<Field> set = ImmutableSet.of();
		private Map<String, Field> map = ImmutableMap.of();

		public Builder(Class<?> type) {
			this.type = type;
		}

		@Override
		public FieldDetail build() {
			FieldDetail instance = new FieldDetail(this);
			validate(instance);
			return instance;
		}

		private static void validate(FieldDetail instance) {
			Preconditions.checkNotNull(instance.type, "'type' cannot be null");
			Preconditions.checkNotNull(instance.set, "'set' cannot be null");
			Preconditions.checkNotNull(instance.map, "'map' cannot be null");
		}

		public Builder set(Set<Field> set) {
			this.set = set;
			return this;
		}

		public Builder map(Map<String, Field> map) {
			this.map = map;
			return this;
		}

		public Set<Field> getSet() {
			return set;
		}

		public void setSet(Set<Field> set) {
			this.set = set;
		}

		public Map<String, Field> getMap() {
			return map;
		}

		public void setMap(Map<String, Field> map) {
			this.map = map;
		}

		public Class<?> getType() {
			return type;
		}
	}

	public Class<?> getType() {
		return type;
	}

	public ImmutableSet<Field> getSet() {
		return set;
	}

	public ImmutableMap<String, Field> getMap() {
		return map;
	}

}
