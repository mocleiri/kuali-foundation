package org.kuali.common.util.tree;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class MutableNode<T> extends AbstractNode<T> {

	protected Optional<MutableNode<T>> mutableParent = Optional.absent();
	protected List<MutableNode<T>> mutableChildren = Lists.newArrayList();
	protected T element;

	public static <T> MutableNode<T> of(T element) {
		return new MutableNode<T>(element);
	}

	protected MutableNode() {
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
	public Optional<Node<T>> getParent() {
		return Optional.<Node<T>> fromNullable(getMutableParent().orNull());
	}

	public Optional<MutableNode<T>> getMutableParent() {
		return mutableParent;
	}

	protected void setMutableParent(Optional<MutableNode<T>> parent) {
		this.mutableParent = parent;
	}

	protected void setParent(MutableNode<T> parent) {
		checkNotNull(parent, "'parent' cannot be null");
		setMutableParent(Optional.of(parent));
	}

	/**
	 * Returns an immutable list of the nodes current children
	 */
	@Override
	public List<Node<T>> getChildren() {
		List<Node<T>> list = Lists.newArrayList();
		for (Node<T> child : mutableChildren) {
			list.add(child);
		}
		return ImmutableList.copyOf(list);
	}

	public void remove(MutableNode<T> child) {
		checkNotNull(child, "'child' cannot be null");
		checkState(isChild(child), "'child' is not a child of this node");
		remove(mutableChildren.indexOf(child));
	}

	public void remove(int index) {
		MutableNode<T> child = mutableChildren.get(index);
		mutableChildren.remove(index);
		child.setMutableParent(Optional.<MutableNode<T>> absent());
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

	public void add(MutableNode<T> child1, MutableNode<T> child2, MutableNode<T> child3, MutableNode<T> child4) {
		add(ImmutableList.of(child1, child2, child3, child4));
	}

	public void add(MutableNode<T> child1, MutableNode<T> child2, MutableNode<T> child3, MutableNode<T> child4, MutableNode<T> child5) {
		add(ImmutableList.of(child1, child2, child3, child4, child5));
	}

	public void add(MutableNode<T> child) {
		checkNotNull(child, "'child' cannot be null");
		add(mutableChildren.size(), child);
	}

	public void add(int index, MutableNode<T> child) {
		// Can't be null
		checkNotNull(child, "'child' cannot be null");

		// If it's already a child, it gets removed from it's current position and then added to the end
		// Thus, index needs to be children.size() - 1 if it's already a child because of how the add method works
		// It removes the child first, (thus decreasing the size of the list by 1 if this node is already a child)
		int actualIndex = isChild(child) ? mutableChildren.size() - 1 : mutableChildren.size();

		// Can't be us, our parent, our grandparent, etc
		checkState(!isAncestor(child), "'child' is an ancestor");

		// Remove this child from it's current parent
		// If the child's parent is us, this decreases our child count by 1 (temporarily)
		if (child.getMutableParent().isPresent()) {
			child.getMutableParent().get().remove(child);
		}

		// Make the child's parent this node
		child.setParent(this);

		// Add the child
		mutableChildren.add(actualIndex, child);
	}

	public void removeAllChildren() {
		for (int i = 0; i < mutableChildren.size(); i++) {
			remove(i);
		}
	}

	/**
	 * Removes the subtree rooted at this node from the tree, giving this node an absent parent. Does nothing if this node is the root of its tree.
	 */
	public void removeFromParent() {
		Optional<MutableNode<T>> parent = getMutableParent();
		if (parent.isPresent()) {
			parent.get().remove(this);
		}
	}
}
