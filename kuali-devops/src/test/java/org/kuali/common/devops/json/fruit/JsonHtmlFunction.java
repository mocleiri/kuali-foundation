package org.kuali.common.devops.json.fruit;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;

import org.kuali.common.util.tree.Node;
import org.springframework.core.convert.TypeDescriptor;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;

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
		strings.addAll(getJava(desc));
		if (node.isLeaf()) {
			strings.add(tr("json", desc.getNode().toString()));
		}
		return "<table border=0>" + Joiner.on("").join(strings) + "</table>";
	}

	protected List<String> getJava(JsonDescriptor desc) {
		if (!desc.getDescriptor().isPresent()) {
			return newArrayList();
		}
		FieldDescriptor fd = desc.getDescriptor().get();
		List<String> strings = newArrayList();
		strings.add(tr("java array", fd.isArray()));
		strings.add(tr("java collection", fd.isCollection()));
		if (fd.isCollection() || fd.isArray()) {
			Optional<TypeDescriptor> etd = fromNullable(fd.getElementTypeDescriptor());
			checkState(etd.isPresent(), "type descriptor cannot be missing here");
			strings.add(tr("java collection type", etd.get().getType().getSimpleName()));
		} else {
			strings.add(tr("java collection type", "n/a"));
		}
		return strings;
	}

	protected String tr(String label, Object value) {
		checkNotBlank(label, "label");
		checkNotNull(value, "value");
		return "<tr><td align=right>" + label + "</td><td>&nbsp;</td><td>" + value.toString() + "</td></tr>";
	}

}
