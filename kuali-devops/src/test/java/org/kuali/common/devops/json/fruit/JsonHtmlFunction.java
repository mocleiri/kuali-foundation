package org.kuali.common.devops.json.fruit;

import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
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
		strings.add(tr("type", desc.getType().getSimpleName()));
		if (desc.getDescriptor().isPresent()) {
			FieldDescriptor fd = desc.getDescriptor().get();
			strings.add(tr("field name", fd.getField().getName()));
		} else {
			strings.add(tr("field name", "n/a"));
		}
		strings.add(tr("json container", desc.getNode().isContainerNode()));
		strings.add(tr("json array", desc.getNode().isArray()));
		return "<table border=0>" + Joiner.on("").join(strings) + "</table>";
	}

	protected String tr(String label, Object value) {
		checkNotBlank(label, "label");
		checkNotNull(value, "value");
		return "<tr><td align=right>" + label + "</td><td>&nbsp;</td><td>" + value.toString() + "</td></tr>";
	}

}
