package org.kuali.common.util.tree;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

public class MutableNode<T> implements Node<T> {

	protected Optional<MutableNode<T>> parent = Optional.absent();
	protected List<MutableNode<T>> children = Lists.newArrayList();
	protected T userObject;

	public MutableNode(T userObject) {
		setUserObject(userObject);
	}

	public void add(MutableNode<T> node) {
		children.add(node);
	}

	@Override
	public boolean isRoot() {
		return !parent.isPresent();
	}

	@Override
	public boolean isLeaf() {
		return children.isEmpty();
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
		MutableNode<T> ancestor = this;
		while (ancestor.getParent().isPresent()) {
			ancestor = ancestor.getParent().get();
			level++;
		}
		return level;
	}

	@Override
	public List<MutableNode<T>> getPath() {
		MutableNode<T> ancestor = this;
		List<MutableNode<T>> list = Lists.newArrayList();
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

	public void setUserObject(T userObject) {
		checkNotNull(userObject, "'userObject' cannot be null");
		this.userObject = userObject;
	}

	@Override
	public T getUserObject() {
		return userObject;
	}

	@Override
	public Optional<MutableNode<T>> getParent() {
		return parent;
	}

	public void setParent(Optional<MutableNode<T>> parent) {
		this.parent = parent;
	}

	public void setParent(MutableNode<T> parent) {
		checkNotNull(parent, "'parent' cannot be null");
		setParent(Optional.of(parent));
	}

	@Override
	public List<MutableNode<T>> getChildren() {
		return children;
	}

	public void setChildren(List<MutableNode<T>> children) {
		checkNotNull(children, "'children' cannot be null");
		this.children = children;
	}

	public boolean isChildNode(MutableNode<T> child) {
		checkNotNull(child, "'child' cannot be null");
		if (children.isEmpty()) {
			return false;
		} else {
			return child.getParent().isPresent() && child.getParent().get() == this;
		}
	}

	public int getIndex(MutableNode<T> child) {
		checkNotNull(child, "'child' cannot be null");
		if (!isChildNode(child)) {
			return -1;
		}
		return children.indexOf(child); // linear search
	}

	public void remove(MutableNode<T> child) {
		checkNotNull(child, "'child' cannot be null");
		checkState(isChildNode(child), "'child' is not a child of this node");
		remove(getIndex(child)); // linear search
	}

	public void remove(int index) {
		MutableNode<T> child = children.get(index);
		children.remove(index);
		child.setParent(Optional.<MutableNode<T>> absent());
	}

}
