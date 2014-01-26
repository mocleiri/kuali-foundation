package org.kuali.common.util.tree.test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.util.tree.ImmutableNode;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Trees;

public class ImmutableNodeTest {

	@Test
	public void test() {
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
			FileUtils.write(new File("/tmp/nodes.htm"), html);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
