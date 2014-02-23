package org.kuali.common.devops.json.fruit;

import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;

import org.kuali.common.util.tree.Node;

import com.google.common.base.Function;
import com.google.common.base.Joiner;

public class JsonHtmlFunction implements Function<Node<JsonDescriptor>, String> {

	@Override
	public String apply(Node<JsonDescriptor> node) {
		checkNotNull(node, "node");
		JsonDescriptor desc = node.getElement();
		List<String> strings = newArrayList();
		strings.add("type: " + desc.getType().getSimpleName());
		if (desc.getDescriptor().isPresent()) {
			FieldDescriptor fd = desc.getDescriptor().get();
			strings.add("name:" + fd.getField().getName());
		} else {
			strings.add("name:none");
		}
		return "<table border=1><tr><td>" + Joiner.on("</td></tr><tr><td>").join(strings) + "</td></tr></table>";
	}

}
