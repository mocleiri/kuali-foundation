package org.kuali.common.util.tree.test;

import org.junit.Test;
import org.kuali.common.util.tree.ImmutableNode;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.NodeElementFunction;
import org.kuali.common.util.tree.Trees;

public class ImmutableNodeTest {

	@Test
	public void test() {
		try {
			MutableNode<String> node = new MutableNode<String>("foo");
			node.add(new MutableNode<String>("bar"));
			node.add(new MutableNode<String>("baz"));
			ImmutableNode<String> immutable = ImmutableNode.copyOf(node);
			String html = Trees.html(immutable,0,NodeElementFunction<T>.make());
			System.out.println(immutable.getElement());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
