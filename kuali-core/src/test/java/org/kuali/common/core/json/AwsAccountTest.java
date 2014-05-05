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
package org.kuali.common.core.json;

import static org.junit.Assert.assertEquals;
import static org.kuali.common.util.PropertyUtils.getStartsWithKeys;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.core.json.api.JsonService;
import org.kuali.common.core.json.jackson.JacksonJsonService;
import org.kuali.common.core.json.jackson.JsonNodeFunction;
import org.kuali.common.core.json.jackson.NestedKeysFunction;
import org.kuali.common.util.base.string.SplitterFunction;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;

public class AwsAccountTest {

	private static final JsonService SERVICE = new JacksonJsonService();

	@Test
	public void test() {
		try {
			Properties props = new Properties();
			props.setProperty("awsAccount.name", "jeff");
			AwsAccount account = AwsAccount.builder().withName("name").withAccountNumber("123").build();
			override(account, props);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void testSymmetry() {
		try {
			Properties props = new Properties();
			props.setProperty("awsAccount.name", "jeff");
			AwsAccount account = AwsAccount.builder().withName("name").withAccountNumber("123").build();
			String json1 = SERVICE.writeString(account);
			AwsAccount deserialized = SERVICE.readString(json1, AwsAccount.class);
			String json2 = SERVICE.writeString(deserialized);
			assertEquals(json1, json2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected static <T> T override(T provided, Properties properties) {
		Properties converted = convert(provided.getClass(), properties);
		ObjectNode pNode = getObjectNode(converted);
		ObjectNode oNode = getObjectNode(provided);
		MutableNode<JsonNode> root1 = new MutableNode<JsonNode>(pNode);
		MutableNode<JsonNode> root2 = new MutableNode<JsonNode>(oNode);
		copy(root1, pNode);
		copy(root2, oNode);
		return null;
	}

	protected static void override(Node<JsonNode> provided, Node<JsonNode> override) {
		if (override.isLeaf()) {
			List<Node<JsonNode>> path = override.getPath();
		}
	}

	protected static Node<JsonNode> findNode(Node<JsonNode> root, Node<JsonNode> leaf) {
		List<Node<JsonNode>> path = leaf.getPath();
		for (Node<JsonNode> element : path) {
		}
		return null;
	}

	protected static Node<JsonNode> findChild(Node<JsonNode> node, Node<JsonNode> child) {
		List<Node<JsonNode>> children = node.getChildren();
		JsonNode jsonNode = child.getElement();
		ObjectNode objectNode = (ObjectNode) jsonNode;
		for (Node<JsonNode> element : children) {
			JsonNode elementJsonNode = element.getElement();

		}
		return null;
	}

	protected static void copy(MutableNode<JsonNode> node, JsonNode json) {
		MutableNode<JsonNode> child = new MutableNode<JsonNode>(json);
		Iterator<JsonNode> itr = json.elements();
		while (itr.hasNext()) {
			copy(child, itr.next());
		}
		node.add(child);
	}

	protected static <T> T override(JsonNode provided, JsonNode override, Class<T> type) {
		if (provided instanceof ValueNode) {
			System.out.println(provided.getClass().getCanonicalName());
			return null;
		} else {
			Iterator<JsonNode> itr = provided.elements();
			while (itr.hasNext()) {
				override(itr.next(), null, null);
			}
			return null;
		}

	}

	protected static String getPrefix(Class<?> type) {
		String name = type.getSimpleName();
		String first = name.substring(0, 1).toLowerCase();
		String rest = name.substring(1);
		return first + rest + ".";
	}

	protected static Properties convert(Class<?> type, Properties properties) {
		String prefix = getPrefix(type);
		List<String> oldKeys = getStartsWithKeys(properties, prefix);
		Properties converted = new Properties();
		for (String oldKey : oldKeys) {
			String newKey = oldKey.substring(prefix.length());
			String value = properties.getProperty(oldKey);
			checkNotNull(value, "value");
			converted.setProperty(newKey, value);
		}
		return ImmutableProperties.copyOf(converted);
	}

	protected static <T> ObjectNode getObjectNode(T instance) {
		String json = SERVICE.writeString(instance);
		return SERVICE.readString(json, ObjectNode.class);
	}

	protected static ObjectNode getObjectNode(Properties properties) {
		String separator = ".";
		Set<String> paths = new SplitterFunction(separator).apply(properties.stringPropertyNames());
		Node<String> node = new NestedKeysFunction(separator).apply(paths);
		return new JsonNodeFunction(separator, properties).apply(node);
	}

}
