package org.kuali.common.devops.json.system;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newTreeSet;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

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
 * 
 */
public class NestedKeysFunction implements Function<Set<String>, Node<String>> {

	public NestedKeysFunction() {
		this(".");
	}

	public NestedKeysFunction(String separator) {
		this(separator, "root");
	}

	public NestedKeysFunction(String separator, String rootNodeElement) {
		checkNotBlank(separator, "separator");
		this.rootNodeElement = checkNotBlank(rootNodeElement, "rootNodeElement");
		this.splitter = Splitter.on(separator);
		this.joiner = Joiner.on(separator);
		this.pathSplitter = new SplitterFunction(separator);
	}

	private final String rootNodeElement;
	private final Splitter splitter;
	private final Joiner joiner;
	private final SplitterFunction pathSplitter;

	@Override
	public Node<String> apply(Set<String> strings) {
		checkNotNull(strings, "strings");
		Set<String> paths = pathSplitter.apply(strings);
		Map<String, MutableNode<String>> nodeMap = getNodeMap(paths);
		return ImmutableNode.copyOf(buildTree(nodeMap));
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
