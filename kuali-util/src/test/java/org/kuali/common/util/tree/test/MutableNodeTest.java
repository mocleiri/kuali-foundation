package org.kuali.common.util.tree.test;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.tree.MutableNode;

public class MutableNodeTest {

	@Test
	public void testMutable() {
		MutableNode<String> a = new MutableNode<String>("a");
		MutableNode<String> b = new MutableNode<String>("b");
		MutableNode<String> c = new MutableNode<String>("c");
		MutableNode<String> one = new MutableNode<String>("1");
		MutableNode<String> two = new MutableNode<String>("2");
		b.add(one, two);
		a.add(b, c);
		Assert.assertFalse(a.getParent().isPresent());
		Assert.assertTrue(b.getParent().isPresent());
		Assert.assertTrue(c.getParent().isPresent());
		Assert.assertTrue(one.getParent().isPresent());
		Assert.assertTrue(two.getParent().isPresent());
	}

}
