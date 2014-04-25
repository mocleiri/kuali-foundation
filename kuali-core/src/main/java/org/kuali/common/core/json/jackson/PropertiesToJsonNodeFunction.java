/**
 * Copyright 2014-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.core.json.jackson;

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
 * Convert the key/value pairs from a {@code Properties} object into Jackson's {@code ObjectNode} tree structure.
 * 
 * In the example below 7 {@code ObjectNode} objects are created, and 4 {@code TextNode} objects are created.
 * 
 * The {@code TextNode} objects contain the value's from the properties object and are leaves in the tree.
 * 
 * <pre>
 * a.1=w              root
 * a.2=x            /      \                  
 * b.1=y           a        b      
 * b.2=z         /   \    /   \
 *              1     2  1     2
 *              |     |  |     |
 *              w     x  y     z
 * </pre>
 */
public class PropertiesToJsonNodeFunction implements Function<Properties, ObjectNode> {

	private static final JsonNodeFactory FACTORY = new JsonNodeFactory(false);

	public PropertiesToJsonNodeFunction() {
		this(".");
	}

	public PropertiesToJsonNodeFunction(String separator) {
		this.joiner = Joiner.on(checkNotBlank(separator, "separator"));
	}

	private final Joiner joiner;

	@Override
	public ObjectNode apply(Properties properties) {
		checkNotNull(properties, "properties");
		Node<String> node = new NestedKeysFunction().apply(properties.stringPropertyNames());
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
		return checkNotNull(properties.getProperty(key), key);
	}
}
