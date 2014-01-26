package org.kuali.common.util.tree;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class MutableNode<T> extends AbstractNode<T> {

	protected Optional<MutableNode<T>> parent = Optional.absent();
	protected List<MutableNode<T>> children = Lists.newArrayList();
	protected T element;

	public static <T> MutableNode<T> of(T element) {
		return new MutableNode<T>(element);
	}

	public MutableNode(T element) {
		setElement(element);
	}

	public void setElement(T element) {
		checkNotNull(element, "'element' cannot be null");
		this.element = element;
	}

	@Override
	public T getElement() {
		return element;
	}

	@Override
	public Optional<MutableNode<T>> getParent() {
		return parent;
	}

	protected void setParent(Optional<MutableNode<T>> parent) {
		this.parent = parent;
	}

	protected void setParent(MutableNode<T> parent) {
		checkNotNull(parent, "'parent' cannot be null");
		setParent(Optional.of(parent));
	}

	@Override
	public List<MutableNode<T>> getChildren() {
		return children;
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

	public void add(List<MutableNode<T>> children) {
		checkNotNull(children, "'children' cannot be null");
		for (MutableNode<T> child : children) {
			add(child);
		}
	}

	public void add(MutableNode<T> child1, MutableNode<T> child2) {
		add(ImmutableList.of(child1, child2));
	}

	public void add(MutableNode<T> child1, MutableNode<T> child2, MutableNode<T> child3) {
		add(ImmutableList.of(child1, child2, child3));
	}

	public void add(MutableNode<T> child) {
		checkNotNull(child, "'child' cannot be null");
		// If it's already a child, it gets removed from it's current position and then added to the end
		// index needs to be children.size() - 1 because of how the add method works
		// It removes the child first, (thus decreasing the size of the list by 1 if this node is already a child)
		int index = isChild(child) ? children.size() - 1 : children.size();
		add(index, child);
	}

	public void add(int index, MutableNode<T> child) {
		// Can't be null
		checkNotNull(child, "'child' cannot be null");

		// Can't be us, our parent, our grandparent, etc
		checkState(!isAncestor(child), "'child' is an ancestor");

		// Remove this child from it's current parent
		// If the child's parent is us, this decreases our child count by 1 (temporarily)
		if (child.getParent().isPresent()) {
			child.getParent().get().remove(child);
		}

		// Make the child's parent this node
		child.setParent(this);

		// Add the child
		children.add(index, child);
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
