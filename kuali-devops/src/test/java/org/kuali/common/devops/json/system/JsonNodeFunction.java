package org.kuali.common.devops.json.system;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.util.tree.Node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.base.Function;

/**
 * 
 */
public class JsonNodeFunction implements Function<Node<String>, JsonNode> {

	private final JsonNodeFactory factory = new JsonNodeFactory(false);

	@Override
	public JsonNode apply(Node<String> node) {
		checkNotNull(node, "node");
		return buildTree(node);
	}

	protected JsonNode buildTree(Node<String> node) {
		if (node.isLeaf()) {
			return new TextNode(node.getElement());
		} else {
			ObjectNode objectNode = new ObjectNode(factory);
			for (Node<String> child : node.getChildren()) {
				JsonNode jsonNode = buildTree(child);
				objectNode.put(child.getElement(), jsonNode);
			}
			return objectNode;
		}
	}

}
