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
		// If it's already a child of this node, it will get removed from it's current spot and added to the end of the list
		// Thus children.size() - 1
		int index = isChild(child) ? children.size() - 1 : children.size();
		insert(index, child);
	}

	public void insert(int index, MutableNode<T> child) {
		// Can't be null
		checkNotNull(child, "'child' cannot be null");

		// Can't be us, our parent, our grandparent, etc
		checkState(!isAncestor(child), "'child' is an ancestor");

		// Remove this child from it's current parent
		if (child.getParent().isPresent()) {
			child.getParent().get().remove(child);
		}

		// Make the childs parent this node
		child.setParent(this);

		// Add the child
		if (index == children.size()) {
			children.add(child);
		} else {
			children.set(index, child);
		}
	}

}
