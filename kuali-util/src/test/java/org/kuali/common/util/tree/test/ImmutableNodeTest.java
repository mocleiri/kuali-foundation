package org.kuali.common.util.tree.test;

import org.junit.Test;
import org.kuali.common.util.tree.ImmutableNode;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;

public class ImmutableNodeTest {

	@Test
	public void test() {
		try {
			Node<String> node = new MutableNode<String>("foo");
			ImmutableNode<String> immutable = ImmutableNode.copyOf(node);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
