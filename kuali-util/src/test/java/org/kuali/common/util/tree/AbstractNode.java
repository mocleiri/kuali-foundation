package org.kuali.common.util.tree;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.collect.Lists;

public abstract class AbstractNode<T> implements Node<T> {

	/**
	 * Return true if this is the root node (ie it has no parent)
	 */
	@Override
	public boolean isRoot() {
		return !getParent().isPresent();
	}

	/**
	 * Return true if this is a leaf node (ie it has no children)
	 */
	@Override
	public boolean isLeaf() {
		return getChildren().isEmpty();
	}

	/**
	 * Returns the number of levels above this node -- the distance from the root to this node. If this node is the root, returns 0.
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

	/**
	 * Returns the path from the root, to get to this node. The last element in the path is this node.
	 */
	@Override
	public List<Node<T>> getPath() {
		Node<T> ancestor = this;
		List<Node<T>> list = Lists.newArrayList();
		list.add(ancestor);
		while (ancestor.getParent().isPresent()) {
			ancestor = ancestor.getParent().get();
			list.add(ancestor);
		}
		return Lists.reverse(list);
	}

	/**
	 * Returns the user objects in the path from the root, to get to this node. The last element is the user object from this node
	 */
	@Override
	public List<T> getUserObjectPath() {
		return Lists.transform(getPath(), new UserObjectFunction<T>());
	}

	/**
	 * Return true if this node is a parent of {@code child}
	 */
	public boolean isChild(AbstractNode<T> child) {
		checkNotNull(child, "'child' cannot be null");
		return child.getParent().isPresent() && child.getParent().get() == this;
	}

	/**
	 * Return true if {@code node} is this node, a parent of this node, a grandparent of this node, etc
	 */
	public boolean isAncestor(AbstractNode<T> node) {
		checkNotNull(node, "'node' cannot be null");
		return getPath().contains(node);
	}

}
