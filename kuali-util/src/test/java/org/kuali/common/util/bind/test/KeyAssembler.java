package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;

import com.google.common.collect.Sets;

public final class KeyAssembler {

	private final Class<?> type;
	private final List<DefaultMutableTreeNode> nodes;

	private KeyAssembler(Builder builder) {
		this.type = builder.type;
		this.nodes = builder.nodes;
	}

	public Set<String> assemble() {
		return Sets.newHashSet();
	}

	public static KeyAssembler make(Class<?> type, List<DefaultMutableTreeNode> nodes) {
		return builder(type, nodes).build();
	}

	public static Builder builder(Class<?> type, List<DefaultMutableTreeNode> nodes) {
		return new Builder(type, nodes);
	}

	public static class Builder {

		private final Class<?> type;
		private final List<DefaultMutableTreeNode> nodes;

		public Builder(Class<?> type, List<DefaultMutableTreeNode> nodes) {
			this.type = type;
			this.nodes = nodes;
		}

		public KeyAssembler build() {
			KeyAssembler instance = new KeyAssembler(this);
			validate(instance);
			return instance;
		}

		private static void validate(KeyAssembler instance) {
			checkNotNull(instance.type, "'type' cannot be null");
			checkNotNull(instance.nodes, "'nodes' cannot be null");
		}
	}
}
