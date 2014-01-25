package org.kuali.common.util.tree;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.collect.Lists;

public abstract class AbstractNode<T> implements Node<T> {

	@Override
	public boolean isRoot() {
		return !getParent().isPresent();
	}

	@Override
	public boolean isLeaf() {
		return getChildren().isEmpty();
	}

	/**
	 * Returns the number of levels above this node -- the distance from the root to this node. If this node is the root, returns 0.
	 * 
	 * @see #getDepth
	 * @return the number of levels above this node
	 */
	@Override
	public int getLevel() {
		int level = 0;
		Node<T> ancestor = this;
		while (ancestor.getParent().isPresent()) {
			ancestor = ancestor.getParent().get();
			level++;
		}
		return level;
	}

	@Override
	public List<Node<T>> getPath() {
		Node<T> ancestor = this;
		List<Node<T>> list = Lists.newArrayList();
		while (ancestor.getParent().isPresent()) {
			list.add(ancestor);
			ancestor = ancestor.getParent().get();
		}
		return Lists.reverse(list);
	}

	@Override
	public List<T> getUserObjectPath() {
		return Lists.transform(getPath(), new UserObjectFunction<T>());
	}

	public boolean isChild(AbstractNode<T> child) {
		checkNotNull(child, "'child' cannot be null");
		return child.getParent().isPresent() && child.getParent().get() == this;
	}

	public boolean isAncestor(AbstractNode<T> node) {
		checkNotNull(node, "'node' cannot be null");
		return getPath().contains(node);
	}

}
