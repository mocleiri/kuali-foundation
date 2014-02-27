package org.kuali.common.devops.json.system;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newTreeSet;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.kuali.common.util.tree.ImmutableNode;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;

/**
 * Converts key/value pairs into correctly nested json. The pair {@code foo.bar=baz} would normally be translated to json as:
 * 
 * <pre>
 * {
 *   "foo.bar" : "baz"
 * }
 * </pre>
 * 
 * This class converts {@code foo.bar=baz} into JsonNode objects corresponding to the nested structure implied by the key:
 * 
 * <pre>
 * {
 *   "foo" : {
 *     "bar" : "baz"
 *   }
 * }
 * </pre>
 */
public class NestedPropertiesFunction implements Function<Properties, Node<String>> {

	private final String rootNodeElement = "properties";
	private final String separator = ".";
	private final Splitter splitter = Splitter.on(separator);
	private final Joiner joiner = Joiner.on(separator);
	private final SplitterFunction pathSplitter = new SplitterFunction(separator);

	@Override
	public Node<String> apply(Properties properties) {
		Set<String> paths = pathSplitter.apply(properties.stringPropertyNames());
		Map<String, MutableNode<String>> nodeMap = getNodeMap(paths);
		return buildTree(nodeMap);
	}

	protected Node<String> buildTree(Map<String, MutableNode<String>> map) {
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
		return ImmutableNode.copyOf(root);
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
