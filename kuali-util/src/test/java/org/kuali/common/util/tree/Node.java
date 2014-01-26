package org.kuali.common.util.tree;

import java.util.List;

import com.google.common.base.Optional;

public interface Node<T> {

	Optional<? extends Node<T>> getParent();

	T getElement();

	int getLevel();

	List<? extends Node<T>> getChildren();

	List<? extends Node<T>> getPath();

	List<T> getElementPath();

	boolean isRoot();

	boolean isLeaf();

}
