package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import org.kuali.common.util.bind.model.BoundTypeDescriptor;
import org.kuali.common.util.tree.Node;
import org.kuali.common.util.tree.Trees;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public final class KeyAssembler implements Assembler<Set<String>> {

	private final BoundTypeDescriptor descriptor;
	
	@Override
	public Set<String> assemble() {
		List<Node<Field>> fields = descriptor.getFields();
		List<Node<Field>> leaves = Trees.getLeaves(fields);
		Set<String> keys = Sets.newHashSet();
		for (Node<Field> leaf : leaves) {
			String key = getKey(leaf);
			keys.add(key);
		}
		return ImmutableSet.copyOf(keys);
	}

	protected String getKey(Node<Field> field) {
		List<Field> path = field.getElementPath();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < path.size(); i++) {
			if (i != 0) {
				sb.append(".");
			}
			sb.append(path.get(i).getName());
		}
		return sb.toString();
	}

	private KeyAssembler(Builder builder) {
		this.descriptor = builder.descriptor;
	}

	public static class Builder implements org.kuali.common.util.build.Builder<KeyAssembler> {

		private BoundTypeDescriptor descriptor;

		public Builder descriptor(BoundTypeDescriptor descriptor) {
			this.descriptor = descriptor;
			return this;
		}

		@Override
		public KeyAssembler build() {
			KeyAssembler instance = new KeyAssembler(this);
			validate(instance);
			return instance;
		}

		private static void validate(KeyAssembler instance) {
			checkNotNull(instance.descriptor, "'descriptor' cannot be null");
		}
	}

}
