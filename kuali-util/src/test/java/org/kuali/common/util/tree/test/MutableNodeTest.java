package org.kuali.common.util.tree.test;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.tree.MutableNode;

public class MutableNodeTest {

	@Test
	public void testMutableBasics() {
		MutableNode<String> root = new MutableNode<String>("root");
		MutableNode<String> a = new MutableNode<String>("a");
		MutableNode<String> b = new MutableNode<String>("b");
		MutableNode<String> one = new MutableNode<String>("1");
		MutableNode<String> two = new MutableNode<String>("2");
		a.add(one, two);
		root.add(a, b);
		Assert.assertFalse(root.getParent().isPresent());
		Assert.assertTrue(a.getParent().isPresent());
		Assert.assertTrue(b.getParent().isPresent());
		Assert.assertTrue(one.getParent().isPresent());
		Assert.assertTrue(two.getParent().isPresent());
		Assert.assertEquals(a.getParent().get(), b.getParent().get());
		Assert.assertTrue(root.isRoot());
		Assert.assertFalse(root.isLeaf());
		Assert.assertFalse(a.isLeaf());
		Assert.assertFalse(a.isRoot());
		Assert.assertTrue(b.isLeaf());
		Assert.assertEquals(0, root.getLevel());
		Assert.assertEquals(1, root.getPath().size());
		Assert.assertEquals(1, root.getElementPath().size());
		Assert.assertEquals(root.getElement(), root.getElementPath().get(0));
	}

}
