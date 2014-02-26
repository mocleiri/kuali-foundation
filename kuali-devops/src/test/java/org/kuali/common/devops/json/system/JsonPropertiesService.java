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
import java.util.SortedSet;

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
		SortedSet<String> sorted = newTreeSet(paths);
		for (String element : sorted) {
			System.out.println(element);
		}
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
				Optional<MutableNode<String>> parent = fromNullable(map.get(parentKey));
				checkState(parent.isPresent(), "unable to locate node -> %s", parentKey.get());
				parent.get().add(child);
			} else {
				root.add(child);
			}
		}
		return ImmutableNode.copyOf(root);
	}

	protected JsonNode buildJsonTree(Node<String> node, Properties properties) {
		if (node.isLeaf()) {
			List<String> tokens = node.getElementPath();
			tokens.remove(0);
			String key = joiner.join(tokens);
			Optional<String> value = fromNullable(properties.getProperty(key));
			checkState(value.isPresent(), "unable to locate value for key -> %s", key);
			return new TextNode(value.get());
		} else {
			ObjectNode objectNode = new ObjectNode(nodeFactory);
			for (Node<String> child : node.getChildren()) {
				objectNode.put(child.getElement(), buildJsonTree(child, properties));
			}
			return objectNode;
		}
	}

	protected Optional<String> getParentKey(String key) {
		List<String> tokens = newArrayList(splitter.splitToList(key));
		if (tokens.size() == 1) {
			return absent();
		} else {
			tokens.remove(tokens.size() - 1);
			return Optional.of(joiner.join(tokens));
		}
	}

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
