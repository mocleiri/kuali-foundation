package org.kuali.common.util.bind.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.tree.Node;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class BoundTypeDescriptor {

	private final Optional<String> prefix;
	private final Class<?> type;
	private final ImmutableList<Node<Field>> fields;

	private BoundTypeDescriptor(Builder builder) {
		this.prefix = builder.prefix;
		this.type = builder.type;
		this.fields = ImmutableList.copyOf(builder.fields);
	}

	public static class Builder {

		private Optional<String> prefix;
		private Class<?> type;
		private List<Node<Field>> fields;

		public Builder prefix(Optional<String> prefix) {
			this.prefix = prefix;
			return this;
		}

		public Builder type(Class<?> type) {
			this.type = type;
			return this;
		}

		public Builder fields(List<Node<Field>> fields) {
			this.fields = fields;
			return this;
		}

		public BoundTypeDescriptor build() {
			BoundTypeDescriptor instance = new BoundTypeDescriptor(this);
			validate(instance);
			return instance;
		}

		private static void validate(BoundTypeDescriptor instance) {
			checkNotNull(instance.type, "'type' cannot be null");
			checkNotNull(instance.prefix, "'prefix' cannot be null");
			checkArgument(!StringUtils.isBlank(instance.prefix.get()), "'prefix' cannot be blank");
		}
	}

	public Optional<String> getPrefix() {
		return prefix;
	}

	public Class<?> getType() {
		return type;
	}

	public ImmutableList<Node<Field>> getFields() {
		return fields;
	}

}
