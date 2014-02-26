package org.kuali.common.devops.json.system;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.newTreeSet;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.kuali.common.devops.json.pojo.JacksonJsonService;
import org.kuali.common.devops.json.pojo.JsonService;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;

public class JsonPropertiesService {

	private final String rootNodeElement = "root";
	private final JsonNodeFactory nodeFactory = new JsonNodeFactory(true);
	private final char separator = '.';
	private final Splitter splitter = Splitter.on(separator);
	private final Joiner joiner = Joiner.on(separator);
	private final JsonService service = new JacksonJsonService();

	public String getJson(Properties properties) {
		Map<String, MutableNode<String>> map = getNodeMap(properties.stringPropertyNames());
		Node<String> node = buildTree(map, properties);
		JsonNode jsonNode = buildJsonTree(node, properties);
		return service.writeString(jsonNode);
	}

	protected Node<String> buildTree(Map<String, MutableNode<String>> map, Properties properties) {
		MutableNode<String> root = MutableNode.of(rootNodeElement);
		for (String key : newTreeSet(map.keySet())) {
			MutableNode<String> child = map.get(key);
			String parentKey = getParentKey(key);
			Optional<MutableNode<String>> parent = fromNullable(map.get(parentKey));
			if (parent.isPresent()) {
				parent.get().add(child);
			} else {
				root.add(child);
			}
		}
		return root;
	}

	protected JsonNode buildJsonTree(Node<String> node, Properties properties) {
		if (node.isLeaf()) {
			List<String> tokens = node.getElementPath();
			tokens.remove(0);
			String key = joiner.join(tokens);
			String value = properties.getProperty(key);
			return new TextNode(value);
		} else {
			ObjectNode objectNode = new ObjectNode(nodeFactory);
			for (Node<String> child : node.getChildren()) {
				objectNode.put(child.getElement(), buildJsonTree(child, properties));
			}
			return objectNode;
		}
	}

	protected String getParentKey(String key) {
		List<String> tokens = splitter.splitToList(key);
		List<String> list = newArrayList();
		for (int i = 0; i < tokens.size() - 1; i++) {
			list.add(tokens.get(i));
		}
		return joiner.join(list);
	}

	protected Map<String, MutableNode<String>> getNodeMap(Set<String> keys) {
		Map<String, MutableNode<String>> map = newHashMap();
		Set<String> paths = newHashSet();
		for (String key : keys) {
			paths.addAll(getPaths(key));
		}
		for (String path : paths) {
			List<String> tokens = splitter.splitToList(path);
			String token = tokens.get(tokens.size() - 1);
			map.put(path, MutableNode.of(token));
		}
		return map;
	}

	/**
	 * <pre>
	 * java.io.tmpdir -> java
	 *                   java.io
	 *                   java.io.tmpdir
	 * </pre>
	 */
	protected Set<String> getPaths(String key) {
		List<String> tokens = splitter.splitToList(key);
		Set<String> paths = newHashSet();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tokens.size(); i++) {
			sb = (i != 0) ? sb.append(separator) : sb;
			sb.append(tokens.get(i));
			paths.add(sb.toString());
		}
		return paths;
	}

}
