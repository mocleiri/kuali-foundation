package org.kuali.common.util.tree;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class MutableNode<T> {

	MutableNode<T> parent;
	List<MutableNode<T>> children = Lists.newArrayList();
	T userObject;

	public MutableNode(T userObject) {
		this.userObject = userObject;
	}

	public void add(MutableNode<T> node) {
		children.add(node);
	}

	public void removeFromParent() {
		this.parent = null;
	}

	/**
	 * Returns the number of levels above this node -- the distance from the root to this node. If this node is the root, returns 0.
	 * 
	 * @see #getDepth
	 * @return the number of levels above this node
	 */
	public int getLevel() {
		int level = 0;
		MutableNode<T> ancestor = this;
		while ((ancestor = ancestor.getParent()) != null) {
			level++;
		}
		return level;
	}

	public List<MutableNode<T>> getPath() {
		MutableNode<T> ancestor = this;
		List<MutableNode<T>> list = Lists.newArrayList();
		while ((ancestor = ancestor.getParent()) != null) {
			list.add(ancestor);
		}
		return Lists.reverse(list);
	}

	public List<T> getUserObjectPath() {
		return Lists.transform(getPath(), new UserObjectFunction<T>());
	}

	public boolean isChildNode(MutableNode<T> node) {
		if (node == null) {
			return false;
		}
		if (getChildCount() == 0) {
			return false;
		}
		return node.getParent() == this;
	}

	public int getChildCount() {
		return children == null ? 0 : children.size();
	}

	private static class UserObjectFunction<T> implements Function<MutableNode<T>, T> {

		@Override
		public T apply(MutableNode<T> node) {
			checkNotNull(node, "'node' cannot be null");
			return node.getUserObject();
		}

	}

	public void setUserObject(T element) {
		this.userObject = element;
	}

	public T getUserObject() {
		return userObject;
	}

	public MutableNode<T> getParent() {
		return parent;
	}

	public void setParent(MutableNode<T> parent) {
		this.parent = parent;
	}

	public List<MutableNode<T>> getChildren() {
		return children;
	}

	public void setChildren(List<MutableNode<T>> children) {
		this.children = children;
	}

}
