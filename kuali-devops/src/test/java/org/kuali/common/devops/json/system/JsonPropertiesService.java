package org.kuali.common.devops.json.system;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newTreeSet;
import static org.kuali.common.devops.json.system.SplitterFunction.newSplitterFunction;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.kuali.common.devops.json.pojo.JacksonJsonService;
import org.kuali.common.devops.json.pojo.JsonService;
import org.kuali.common.util.tree.ImmutableNode;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;

public class JsonPropertiesService {

	private final String rootNodeElement = "root";
	private final JsonNodeFactory nodeFactory = new JsonNodeFactory(true);
	private final char separator = '.';
	private final Splitter splitter = Splitter.on(separator);
	private final Joiner joiner = Joiner.on(separator);
	private final JsonService service = new JacksonJsonService();
	private final SplitterFunction pathSplitter = newSplitterFunction(separator, false);

	public String getJson(Properties properties) {
		Set<String> paths = pathSplitter.apply(properties.stringPropertyNames());
		Map<String, MutableNode<String>> map = getNodeMap(paths);
		Node<String> node = buildTree(map);
		JsonNode jsonNode = buildJsonTree(node, properties);
		return service.writeString(jsonNode);
	}

	/**
	 * Create a new json tree mimicking the structure of the regular tree rooted at node.
	 * 
	 * The leaves of the json tree contain the values from the properties object.
	 */
	protected Node<String> buildTree(Map<String, MutableNode<String>> map) {
		MutableNode<String> root = MutableNode.of(rootNodeElement);
		for (String key : newTreeSet(map.keySet())) {
			MutableNode<String> child = map.get(key);
			Optional<String> parentKey = getParentKey(key);
			if (parentKey.isPresent()) {
				String mapKey = parentKey.get();
				MutableNode<String> parent = map.get(mapKey);
				checkState(parent != null, "unable to locate node -> %s", mapKey);
				parent.add(child);
			} else {
				root.add(child);
			}
		}
		return ImmutableNode.copyOf(root);
	}

	/**
	 * Create a new json tree mimicking the structure of the regular tree rooted at node.
	 * 
	 * The leaves of the json tree contain the values from the properties object.
	 */
	protected JsonNode buildJsonTree(Node<String> node, Properties properties) {
		if (node.isLeaf()) {
			// base case, return a new text node containing the property value corresponding to this node of the tree
			return buildTextNode(node, properties);
		} else {
			// recursive case, build a json tree that copies the regular tree rooted at node
			return buildObjectNode(node, properties);
		}
	}

	/**
	 * Create a new json object node that copes the regular tree rooted at node.
	 * 
	 * The leaves of the json tree contain the values from the properties object.
	 */
	protected ObjectNode buildObjectNode(Node<String> node, Properties properties) {

		// Create a new json node to hold the contents of the tree rooted at the regular node
		ObjectNode objectNode = new ObjectNode(nodeFactory);

		// Recurse down the regular tree creating and linking in json trees as we go
		for (Node<String> child : node.getChildren()) {

			// Create a new JsonNode, this will be either an ObjectNode or a TextNode
			JsonNode jsonNode = buildJsonTree(child, properties);

			// Add the new json node into the json tree using the name from the regular node
			objectNode.put(child.getElement(), jsonNode);
		}

		// Return the fully populated tree
		return objectNode;
	}

	/**
	 * Create a new TextNode based on the property value stored under the key corresponding to the element path from the regular node.
	 */
	protected TextNode buildTextNode(Node<String> node, Properties properties) {
		// The key to the property file is based on the node's position in the tree
		List<String> tokens = node.getElementPath();

		// Remove the token representing the root node we've superficially added
		tokens.remove(0);

		// Getting the property key is now as simple as joining the tokens
		String key = joiner.join(tokens);

		// Extract a property value
		Optional<String> value = fromNullable(properties.getProperty(key));

		// Make sure the properties object contains a value for this key
		checkState(value.isPresent(), "unable to locate value for key -> %s", key);

		// Create a new text node based on the property value
		return new TextNode(value.get());
	}

	/**
	 * <pre>
	 * java.class.path -> java.class
	 * java.class      -> java
	 * java            -> Optional.absent()
	 * </pre>
	 */
	protected Optional<String> getParentKey(String key) {
		List<String> tokens = newArrayList(splitter.splitToList(key));
		if (tokens.size() == 1) {
			return absent();
		} else {
			tokens.remove(tokens.size() - 1);
			return Optional.of(joiner.join(tokens));
		}
	}

	/**
	 * <pre>
	 * java.class.path -> key  == java.class.path
	 *                    node == path
	 * </pre>
	 */
	protected Map<String, MutableNode<String>> getNodeMap(Set<String> paths) {
		Map<String, MutableNode<String>> map = newHashMap();
		for (String path : paths) {
			List<String> tokens = splitter.splitToList(path);
			String nodeElement = tokens.get(tokens.size() - 1);
			MutableNode<String> node = MutableNode.of(nodeElement);
			map.put(path, node);
		}
		return ImmutableMap.copyOf(map);
	}

}
