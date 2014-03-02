package org.kuali.common.util.json.jackson;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newTreeSet;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kuali.common.util.tree.ImmutableNode;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;

/**
 * Convert a set of strings containing paths into a tree structure.
 * 
 * <pre>
 * a               a
 * a.b             |                    
 * a.b.1           b          
 * a.b.2          / \
 *               1   2
 * </pre>
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
	}

	private final String rootNodeElement;
	private final Splitter splitter;
	private final Joiner joiner;

	@Override
	public Node<String> apply(Set<String> paths) {
		checkNotNull(paths, "paths");
		Map<String, MutableNode<String>> nodeMap = getNodeMap(paths);
		return ImmutableNode.copyOf(buildTree(nodeMap));
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

	public String getRootNodeElement() {
		return rootNodeElement;
	}

	public Splitter getSplitter() {
		return splitter;
	}

	public Joiner getJoiner() {
		return joiner;
	}

}
