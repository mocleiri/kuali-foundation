package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.google.common.collect.Lists;

public class Trees {

	@SuppressWarnings("unchecked")
	public static List<DefaultMutableTreeNode> children(DefaultMutableTreeNode node) {
		checkNotNull(node, "'node' cannot be null");
		return asList(node.children());
	}

	@SuppressWarnings("unchecked")
	public static List<DefaultMutableTreeNode> breadthFirst(DefaultMutableTreeNode node) {
		checkNotNull(node, "'node' cannot be null");
		return asList(node.breadthFirstEnumeration());
	}

	@SuppressWarnings("unchecked")
	public static List<DefaultMutableTreeNode> depthFirst(DefaultMutableTreeNode node) {
		checkNotNull(node, "'node' cannot be null");
		return asList(node.depthFirstEnumeration());
	}

	public static List<DefaultMutableTreeNode> asList(Enumeration<DefaultMutableTreeNode> enumeration) {
		checkNotNull(enumeration, "'enumeration' cannot be null");
		List<DefaultMutableTreeNode> nodes = Lists.newArrayList();
		while (enumeration.hasMoreElements()) {
			DefaultMutableTreeNode element = enumeration.nextElement();
			nodes.add(element);
		}
		return nodes;
	}

}
