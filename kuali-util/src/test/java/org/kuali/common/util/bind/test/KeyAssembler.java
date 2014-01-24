package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.kuali.common.util.bind.api.BindingAlias;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public final class KeyAssembler {

	private final Class<?> type;
	private final List<DefaultMutableTreeNode> nodes;

	private KeyAssembler(Builder builder) {
		this.type = builder.type;
		this.nodes = builder.nodes;
	}

	public Set<String> assemble() {
		Optional<String> prefix = Prefixes.get(type);
		List<DefaultMutableTreeNode> combined = combine(nodes);
		SortedSet<String> keys = Sets.newTreeSet();
		for (DefaultMutableTreeNode node : combined) {
			List<Field> fields = getFieldPath(node);
			keys.addAll(getKeys(prefix, fields));
		}
		return keys;
	}

	protected List<String> getKeys(Optional<String> prefix, List<Field> fields) {
		List<String> keys = Lists.newArrayList();
		StringBuilder sb = new StringBuilder();
		if (prefix.isPresent()) {
			sb.append(prefix.get());
		}
		for (int i = 0; i < fields.size() - 1; i++) {
			Optional<String> fieldPrefix = Prefixes.get(fields.get(i));
			if (fieldPrefix.isPresent()) {
				sb.append(".");
				sb.append(fieldPrefix.get());
			}
		}
		Field field = fields.get(fields.size() - 1);
		if (field.isAnnotationPresent(BindingAlias.class)) {
			String[] aliases = field.getAnnotation(BindingAlias.class).value();
			for (String alias : aliases) {
				keys.add(sb.toString() + "." + alias);
			}
			if (field.getAnnotation(BindingAlias.class).includeFieldName()) {
				keys.add(sb.toString() + "." + field.getName());
			}
		} else {
			keys.add(sb.toString() + "." + field.getName());
		}
		return keys;
	}

	protected List<Field> getFieldPath(DefaultMutableTreeNode node) {
		TreeNode[] path = node.getPath();
		List<Field> fields = Lists.newArrayList();
		for (TreeNode element : path) {
			DefaultMutableTreeNode mutable = (DefaultMutableTreeNode) element;
			Field field = (Field) mutable.getUserObject();
			fields.add(field);
		}
		return fields;
	}

	protected String getKey(Optional<String> prefix, DefaultMutableTreeNode node) {
		return null;
	}

	protected List<DefaultMutableTreeNode> combine(List<DefaultMutableTreeNode> nodes) {
		List<DefaultMutableTreeNode> combined = Lists.newArrayList();
		for (DefaultMutableTreeNode node : nodes) {
			combined.addAll(Trees.breadthFirst(node));
		}
		return combined;
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
