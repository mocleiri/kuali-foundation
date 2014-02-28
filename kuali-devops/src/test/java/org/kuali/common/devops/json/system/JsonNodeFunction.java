package org.kuali.common.devops.json.system;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.tree.Node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.base.Function;
import com.google.common.base.Joiner;

/**
 * 
 */
public class JsonNodeFunction implements Function<Node<String>, ObjectNode> {

	private static final JsonNodeFactory FACTORY = new JsonNodeFactory(false);

	public JsonNodeFunction(String separator, Properties properties) {
		this.properties = checkNotNull(properties, "properties");
		this.joiner = Joiner.on(checkNotBlank(separator, "separator"));
	}

	private final Properties properties;
	private final Joiner joiner;

	@Override
	public ObjectNode apply(Node<String> node) {
		checkNotNull(node, "node");
		return (ObjectNode) buildTree(node, properties);
	}

	protected JsonNode buildTree(Node<String> node, Properties properties) {
		if (node.isLeaf()) {
			return new TextNode(getValue(node, properties));
		} else {
			ObjectNode objectNode = new ObjectNode(FACTORY);
			for (Node<String> child : node.getChildren()) {
				JsonNode jsonNode = buildTree(child, properties);
				objectNode.put(child.getElement(), jsonNode);
			}
			return objectNode;
		}
	}

	protected String getValue(Node<String> leaf, Properties properties) {
		checkArgument(leaf.isLeaf(), "not a leaf node");
		List<String> tokens = newArrayList(leaf.getElementPath());
		tokens.remove(0);
		String key = joiner.join(tokens);
		String value = properties.getProperty(key);
		return value;
	}
}
