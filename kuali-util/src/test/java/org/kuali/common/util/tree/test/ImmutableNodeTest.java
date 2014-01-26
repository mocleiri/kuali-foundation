package org.kuali.common.util.tree.test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.tree.ImmutableNode;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;
import org.kuali.common.util.tree.Trees;

public class ImmutableNodeTest {

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

	@Test
	public void test1() {
		try {
			MutableNode<String> a = new MutableNode<String>("a");
			MutableNode<String> b = new MutableNode<String>("b");
			MutableNode<String> c = new MutableNode<String>("c");
			MutableNode<String> one = new MutableNode<String>("1");
			MutableNode<String> two = new MutableNode<String>("2");
			b.add(one, two);
			a.add(b, c);
			ImmutableNode<String> immutable = ImmutableNode.copyOf(a);
			String html = Trees.html("Unit Test", immutable);
			File file = new CanonicalFile(System.getProperty("java.io.tmpdir"), "nodes.htm");
			FileUtils.write(file, html);
			System.out.println(file);
			Node<String> b1 = immutable.getChildren().get(0);
			Node<String> c1 = immutable.getChildren().get(1);
			Node<String> a1 = c1.getParent().get();
			Node<String> b2 = a1.getChildren().get(0);
			System.out.println(b1 + " " + b1.getElement());
			System.out.println(b2 + " " + b2.getElement());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
