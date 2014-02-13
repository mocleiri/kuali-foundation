package org.kuali.common.util.bind.breakfast.immutable;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.lang.reflect.Field;

import org.kuali.common.util.tree.Node;

import com.google.common.base.Function;

public class FieldNameFunction implements Function<Node<Field>, String> {

	public static FieldNameFunction make() {
		return new FieldNameFunction();
	}

	@Override
	public String apply(Node<Field> node) {
		checkNotNull(node, "node");
		checkNotNull(node.getElement(), "node.element");
		return node.getElement().getName();
	}

}
