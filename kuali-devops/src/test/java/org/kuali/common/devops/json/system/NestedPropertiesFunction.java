package org.kuali.common.devops.json.system;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newTreeSet;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;

/**
 * 
 */
public class NestedPropertiesFunction implements Function<Properties, Node<String>> {

	private final String rootNodeElement = "properties";
	private final String separator = ".";
	private final Splitter splitter = Splitter.on(separator);
	private final Joiner joiner = Joiner.on(separator);
	private final SplitterFunction pathSplitter = new SplitterFunction(separator);

	@Override
	public Node<String> apply(Properties properties) {
		checkNotNull(properties, "properties");
		Set<String> paths = pathSplitter.apply(properties.stringPropertyNames());
		Map<String, MutableNode<String>> nodeMap = getNodeMap(paths);
		MutableNode<String> node = buildTree(nodeMap);
		addValues(node, properties);
		return node;
	}

	protected void addValues(MutableNode<String> node, Properties properties) {
		if (node.isLeaf()) {
			MutableNode<String> valueNode = getValueNode(node, properties);
			node.add(valueNode);
		} else {
			for (Node<String> child : node.getChildren()) {
				addValues((MutableNode<String>) child, properties);
			}
		}
	}

	protected MutableNode<String> getValueNode(MutableNode<String> node, Properties properties) {
		checkArgument(node.isLeaf(), "node must be a leaf");
		String key = getPropertyKey(node);
		String value = properties.getProperty(key);
		checkState(value != null, "no value for [%s]", key);
		return MutableNode.of(value);
	}

	protected String getPropertyKey(Node<String> node) {
		checkArgument(node.isLeaf(), "node must be a leaf");
		List<String> path = newArrayList(node.getElementPath());
		path.remove(0);
		return joiner.join(path);
	}

	protected MutableNode<String> buildTree(Map<String, MutableNode<String>> map) {
		MutableNode<String> root = MutableNode.of(rootNodeElement);
		for (String key : newTreeSet(map.keySet())) {
			MutableNode<String> child = map.get(key);
			Optional<String> parentKey = getParentKey(key);
			if (parentKey.isPresent()) {
				MutableNode<String> parent = map.get(parentKey.get());
				checkState(parent != null, "unable to locate node -> %s", parentKey.get());
				parent.add(child);
			} else {
				root.add(child);
			}
		}
		return root;
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
		return map;
	}

}
