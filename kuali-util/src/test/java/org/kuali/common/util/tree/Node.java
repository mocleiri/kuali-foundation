package org.kuali.common.util.tree;

import java.util.List;

import com.google.common.base.Optional;

public interface Node<T> {

	Optional<? extends Node<T>> getParent();

	T getUserObject();

	int getLevel();

	List<? extends Node<T>> getChildren();

	List<Node<T>> getPath();

	List<T> getUserObjectPath();

	boolean isRoot();

	boolean isLeaf();

}
