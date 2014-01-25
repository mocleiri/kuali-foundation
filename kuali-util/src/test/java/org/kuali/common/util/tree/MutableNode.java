package org.kuali.common.util.tree;

import java.util.List;

import com.google.common.collect.Lists;

public class MutableNode<T> {

	protected MutableNode<T> parent;
	protected List<MutableNode<T>> children = Lists.newArrayList();
	protected T element;

	public MutableNode(T element) {
		this.parent = null;
		this.element = element;
	}

	public void add(MutableNode<T> node) {
		children.add(node);
	}

	public void setElement(T element) {
		this.element = element;
	}

	public T getElement() {
		return element;
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
