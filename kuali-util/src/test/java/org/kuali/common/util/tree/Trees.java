package org.kuali.common.util.tree;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

public class Trees {

	public static <T> String html(String title, Node<T> node) {
		Function<Node<T>, String> converter = NodeStringFunction.create();
		return html(title, ImmutableList.of(node), converter);
	}

	public static <T> String html(String title, Node<T> node, Function<Node<T>, String> converter) {
		return html(title, ImmutableList.of(node), converter);
	}

	public static <T> String html(String title, List<Node<T>> nodes) {
		Function<Node<T>, String> converter = NodeStringFunction.create();
		return html(title, nodes, converter);
	}

	public static <T> String html(String title, List<Node<T>> nodes, Function<Node<T>, String> converter) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table border=\"1\">\n");
		sb.append(" <th>" + title + "</th>\n");
		sb.append(" <tr>\n");
		sb.append("  <td>\n");
		for (Node<T> node : nodes) {
			sb.append(html(node, 3, converter));
		}
		sb.append("  </td>\n");
		sb.append(" </tr>\n");
		sb.append("</table>\n");
		return sb.toString();
	}

	public static <T> String html(Node<T> node) {
		Function<Node<T>, String> converter = NodeStringFunction.create();
		return html(node, 0, converter);
	}

	public static <T> String html(Node<T> node, Function<Node<T>, String> converter) {
		return html(node, 0, converter);
	}

	public static <T> String html(Node<T> node, int indent, Function<Node<T>, String> converter) {
		StringBuilder sb = new StringBuilder();
		String prefix = StringUtils.repeat(" ", indent);
		sb.append(prefix + "<table border=\"1\">\n");
		sb.append(prefix + " <tr>\n");
		sb.append(prefix + "  <td>" + converter.apply(node) + "</td>\n");
		List<Node<T>> children = node.getChildren();
		if (!children.isEmpty()) {
			sb.append(prefix + "  <td>\n");
			for (Node<T> child : children) {
				sb.append(html(child, indent + 3, converter));
			}
			sb.append(prefix + "  </td>\n");
		}
		sb.append(prefix + " </tr>\n");
		sb.append(prefix + "</table>\n");
		return sb.toString();
	}

}
