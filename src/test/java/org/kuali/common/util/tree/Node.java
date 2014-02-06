package org.kuali.common.util.tree;

import java.util.List;

import com.google.common.base.Optional;

/**
 * Type safe tree data structure
 */
public interface Node<T> {

	Optional<Node<T>> getParent();

	T getElement();

	int getLevel();

	List<Node<T>> getChildren();

	List<Node<T>> getPath();

	List<T> getElementPath();

	boolean isRoot();

	boolean isLeaf();

	boolean isChild(Node<T> parent);

	boolean isParent(Node<T> child);

	boolean isAncestor(Node<T> parent);

}
