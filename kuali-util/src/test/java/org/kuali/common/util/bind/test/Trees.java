package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public class Trees {

	public static String html(DefaultMutableTreeNode node) {
		int level = node.getLevel();
		String prefix = StringUtils.repeat(" ", level);
		StringBuilder sb = new StringBuilder();
		sb.append(prefix + "<table border=\"1\">\n");
		sb.append(prefix + "<tr>\n");
		sb.append(prefix + "<td>" + node.getUserObject().toString() + "</td>\n");
		List<DefaultMutableTreeNode> children = children(node);
		if (!children.isEmpty()) {
			sb.append(prefix + "<td>\n");
			for (DefaultMutableTreeNode child : children) {
				sb.append(prefix + html(child));
			}
			sb.append(prefix + "</td>\n");
		}
		sb.append(prefix + "</tr>\n");
		sb.append(prefix + "</table>\n");
		return sb.toString();
	}

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
