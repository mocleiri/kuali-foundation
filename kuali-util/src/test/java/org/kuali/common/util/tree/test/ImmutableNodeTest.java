package org.kuali.common.util.tree.test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.util.bind.function.NodeElementFunction;
import org.kuali.common.util.tree.ImmutableNode;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;
import org.kuali.common.util.tree.Trees;

import com.google.common.base.Function;

public class ImmutableNodeTest {

	@Test
	public void test() {
		try {
			MutableNode<String> node = new MutableNode<String>("foo");
			node.add(new MutableNode<String>("bar"));
			node.add(new MutableNode<String>("baz"));
			ImmutableNode<String> immutable = ImmutableNode.copyOf(node);
			Function<Node<String>, String> function = NodeElementFunction.of();
			String html = Trees.html(immutable, 0, function);
			FileUtils.write(new File("/tmp/nodes.htm"), html);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
