package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import org.kuali.common.util.Annotations;
import org.kuali.common.util.bind.api.BindingAlias;
import org.kuali.common.util.bind.model.BoundTypeDescriptor;
import org.kuali.common.util.tree.Node;
import org.kuali.common.util.tree.Trees;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public final class KeyAssembler implements Assembler<Set<String>> {

	private final BoundTypeDescriptor descriptor;

	@Override
	public Set<String> assemble() {
		List<Node<Field>> fields = descriptor.getFields();
		List<Node<Field>> leaves = Trees.getLeaves(fields);
		Set<String> keys = Sets.newHashSet();
		for (Node<Field> leaf : leaves) {
			Set<String> fieldKeys = getKeys(leaf);
			keys.addAll(fieldKeys);
		}
		return ImmutableSet.copyOf(keys);
	}

	protected Set<String> getKeys(Node<Field> node) {
		checkArgument(node.isLeaf(), "'node' is not a leaf");
		List<String> pathTokens = getPathTokens(node);
		List<String> leafTokens = getLeafTokens(node);
		List<List<String>> listOfLists = Lists.newArrayList();
		for (String leafToken : leafTokens) {
			List<String> tokens = Lists.newArrayList(pathTokens);
			tokens.add(leafToken);
			listOfLists.add(tokens);
		}
		Joiner joiner = Joiner.on('.');
		Set<String> keys = Sets.newHashSet();
		for (List<String> list : listOfLists) {
			String key = joiner.join(list.iterator());
			keys.add(key);
		}
		return keys;
	}

	protected List<String> getLeafTokens(Node<Field> node) {
		Field field = node.getElement();
		Optional<BindingAlias> annotation = Annotations.get(node.getElement(), BindingAlias.class);
		if (!annotation.isPresent()) {
			return ImmutableList.of(field.getName());
		}
		List<String> tokens = Lists.newArrayList();
		String[] aliases = annotation.get().value();
		for (String alias : aliases) {
			tokens.add(alias);
		}
		if (annotation.get().includeFieldName()) {
			tokens.add(field.getName());
		}
		return tokens;
	}

	protected List<String> getPathTokens(Node<Field> node) {
		List<Field> path = node.getElementPath();
		List<String> tokens = Lists.newArrayList();
		for (Field element : path) {
			tokens.add(element.getName());
		}
		tokens.remove(tokens.size() - 1);
		return tokens;
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
