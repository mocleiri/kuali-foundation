package org.kuali.common.util.bind.impl;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.lang.reflect.Field;

import org.kuali.common.util.tree.Node;

import com.google.common.base.Function;

// enum singleton pattern
public enum FieldNameFunction implements Function<Node<Field>, String> {
	INSTANCE;

	@Override
	public String apply(Node<Field> node) {
		checkNotNull(node, "node");
		checkNotNull(node.getElement(), "node.element");
		return node.getElement().getName();
	}

}
