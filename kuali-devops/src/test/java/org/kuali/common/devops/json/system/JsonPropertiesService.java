package org.kuali.common.devops.json.system;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.newTreeSet;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

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
import com.google.common.collect.ImmutableSet;

public class JsonPropertiesService {

	private final String rootNodeElement = "root";
	private final JsonNodeFactory nodeFactory = new JsonNodeFactory(true);
	private final char separator = '.';
	private final Splitter splitter = Splitter.on(separator);
	private final Joiner joiner = Joiner.on(separator);
	private final JsonService service = new JacksonJsonService();

	public String getJson(Properties properties) {
		Set<String> paths = getPaths(properties.stringPropertyNames());
		Map<String, MutableNode<String>> map = getNodeMap(paths);
		Node<String> node = buildTree(map);
		JsonNode jsonNode = buildJsonTree(node, properties);
		return service.writeString(jsonNode);
	}

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

	protected JsonNode buildJsonTree(Node<String> node, Properties properties) {
		if (node.isLeaf()) {
			// base case, return a new text node containing the property value corresponding to this node of the tree
			return buildTextNode(node, properties);
		} else {
			// recursively build a json tree that copies the regular tree rooted at node
			return buildObjectNode(node, properties);
		}
	}

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

	/**
	 * Convert a series of dot separated strings into a unique set of individual path elements.
	 * 
	 * <pre>
	 * java.class.path    -> java
	 * java.class.version    java.class
	 *                       java.class.path
	 *                       java.class.version
	 * </pre>
	 * 
	 * @throws IllegalArgumentException
	 *             If splitting the individual keys into path elements produces blank tokens
	 */
	protected Set<String> getPaths(Set<String> keys) {
		Set<String> paths = newHashSet();
		for (String key : keys) {
			paths.addAll(getPaths(key));
		}
		return ImmutableSet.copyOf(paths);
	}

	/**
	 * Convert a dot separated string into a unique set of individual path elements.
	 * 
	 * <pre>
	 * java.class.path -> java
	 *                    java.class
	 *                    java.class.path
	 * </pre>
	 * 
	 * @throws IllegalArgumentException
	 *             If splitting the key into path elements produces blank tokens or duplicates
	 */
	protected Set<String> getPaths(String key) {

		// Split the key into tokens
		List<String> tokens = splitter.splitToList(key);

		// Setup some storage for the paths we are creating
		Set<String> paths = newHashSet();

		// Allocate a string builder
		StringBuilder sb = new StringBuilder();

		// Iterate over the tokens to create path elements
		for (int i = 0; i < tokens.size(); i++) {

			// append the separator char unless this is the first loop iteration
			sb = (i != 0) ? sb.append(separator) : sb;

			// Extract the token, checking to make sure it isn't blank
			String token = checkNotBlank(tokens.get(i), "token");

			// Append the current token to create a new path element
			String path = sb.append(token).toString();

			// Add the new path element to our set
			// TODO I think this checkArgument call is superfluous now that checkNotBlank is called on each token
			checkArgument(paths.add(path), "%s is a duplicate path element", path);
		}

		// Return what we've got
		return ImmutableSet.copyOf(paths);
	}

}
