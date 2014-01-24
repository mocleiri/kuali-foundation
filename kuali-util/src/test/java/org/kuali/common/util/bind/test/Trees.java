package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class Trees {

	public static String html(String title, List<DefaultMutableTreeNode> nodes, Function<DefaultMutableTreeNode, String> converter) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table border=\"1\">\n");
		sb.append(" <th>" + title + "</th>\n");
		sb.append(" <tr>\n");
		sb.append("  <td>\n");
		for (DefaultMutableTreeNode node : nodes) {
			sb.append(html(node, 3, converter));
		}
		sb.append("  </td>\n");
		sb.append(" </tr>\n");
		sb.append("</table>\n");
		return sb.toString();
	}

	public static String html(DefaultMutableTreeNode node, int indent, Function<DefaultMutableTreeNode, String> converter) {
		StringBuilder sb = new StringBuilder();
		String prefix = StringUtils.repeat(" ", indent);
		sb.append(prefix + "<table border=\"1\">\n");
		sb.append(prefix + " <tr>\n");
		sb.append(prefix + "  <td>" + converter.apply(node) + "</td>\n");
		List<DefaultMutableTreeNode> children = children(node);
		if (!children.isEmpty()) {
			sb.append(prefix + "  <td>\n");
			for (DefaultMutableTreeNode child : children) {
				sb.append(html(child, indent + 3, converter));
			}
			sb.append(prefix + "  </td>\n");
		}
		sb.append(prefix + " </tr>\n");
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
			nodes.add(enumeration.nextElement());
		}
		return nodes;
	}

}
