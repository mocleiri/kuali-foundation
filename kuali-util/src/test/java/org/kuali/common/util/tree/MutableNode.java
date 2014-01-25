package org.kuali.common.util.tree;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

public class MutableNode<T> extends AbstractNode<T> {

	protected Optional<MutableNode<T>> parent = Optional.absent();
	protected List<MutableNode<T>> children = Lists.newArrayList();
	protected T userObject;

	public MutableNode(T userObject) {
		setUserObject(userObject);
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
	public List<? extends Node<T>> getChildren() {
		return children;
	}

	public void setChildren(List<MutableNode<T>> children) {
		checkNotNull(children, "'children' cannot be null");
		this.children = children;
	}

	public void remove(MutableNode<T> child) {
		checkNotNull(child, "'child' cannot be null");
		checkState(isChild(child), "'child' is not a child of this node");
		remove(children.indexOf(child));
	}

	public void remove(int index) {
		MutableNode<T> child = children.get(index);
		children.remove(index);
		child.setParent(Optional.<MutableNode<T>> absent());
	}

	public void add(MutableNode<T> child) {
		checkNotNull(child, "'child' cannot be null");
		insert(children.size(), child);
	}

	public void insert(final int index, MutableNode<T> child) {
		// Can't be null
		checkNotNull(child, "'child' cannot be null");

		// Can't be us, our parent, our grandparent, etc
		checkState(!isAncestor(child), "'child' is an ancestor");

		// Quietly handle the case where we already have this child in our list of children
		int actualIndex = isChild(child) ? index - 1 : index;

		// Remove this child from it's current parent
		// If the child's parent is us, this decreases our child count by 1 (temporarily)
		if (child.getParent().isPresent()) {
			child.getParent().get().remove(child);
		}

		// Make the childs parent this node
		child.setParent(this);

		// Add the child
		if (actualIndex == children.size()) {
			children.add(child);
		} else {
			children.set(actualIndex, child);
		}
	}

	public void removeAllChildren() {
		for (int i = 0; i < children.size(); i++) {
			remove(i);
		}
	}

	public void removeFromParent() {
		Optional<MutableNode<T>> parent = getParent();
		if (parent.isPresent()) {
			parent.get().remove(this);
		}
	}
}
