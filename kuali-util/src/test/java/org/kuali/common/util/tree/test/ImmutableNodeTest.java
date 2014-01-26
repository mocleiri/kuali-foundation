package org.kuali.common.util.tree.test;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.tree.ImmutableNode;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class ImmutableNodeTest {

	@Test
	public void testBasics() {
		MutableNode<String> root = new MutableNode<String>("root");
		MutableNode<String> a = new MutableNode<String>("a");
		MutableNode<String> b = new MutableNode<String>("b");
		MutableNode<String> one = new MutableNode<String>("1");
		MutableNode<String> two = new MutableNode<String>("2");
		a.add(one, two);
		root.add(a, b);
		ImmutableNode<String> immutable = ImmutableNode.of(root);
		Assert.assertEquals(immutable.getElement(), root.getElement());
		ImmutableList<? extends Node<String>> children = immutable.getChildren();
		Node<String> newA = children.get(0);
		Optional<? extends Node<String>> parent = newA.getParent();
		Node<String> newRoot = parent.get();
		System.out.println(immutable);
		System.out.println(newRoot);
		Assert.assertTrue(newRoot == immutable);
	}
}
