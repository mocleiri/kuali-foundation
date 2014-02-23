package org.kuali.common.devops.json.fruit;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.util.tree.Node;

import com.google.common.base.Function;

public class JsonHtmlFunction implements Function<Node<JsonDescriptor>, String> {

	@Override
	public String apply(Node<JsonDescriptor> node) {
		checkNotNull(node, "node");
		return node.getElement().getType().getSimpleName();
	}

}
