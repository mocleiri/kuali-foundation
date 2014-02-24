package org.kuali.common.devops.json.fruit;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.build.BuilderUtils.findPublicStaticBuilderClass;
import static org.springframework.core.CollectionFactory.createCollection;
import static org.springframework.util.ReflectionUtils.findField;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.builder.Builder;
import org.kuali.common.util.tree.Node;
import org.springframework.core.convert.TypeDescriptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;

public class JsonHtmlFunction implements Function<Node<JsonDescriptor>, String> {

	ObjectMapper mapper = new ObjectMapper();
	private int count = 1;

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

		// If the node is a JSON array, introspect the corresponding java field in order to divine
		// what type of collection to create, otherwise just return the type of the field
		// eg, a field declared as List<String> means we need to create String instances, and put them in a list
		Class<?> typeToCreate = findTypeToCreate(desc);
		strings.add(tr("type to create", typeToCreate.getSimpleName()));

		// This is a builder capable of creating the elements to go into the collection or just a builder for the element itself
		Optional<Class<Builder<?>>> builderClass = getBuilderClass(typeToCreate);
		if (builderClass.isPresent()) {
			strings.add(tr("builder", builderClass.get().getCanonicalName()));
		}

		if (node.isLeaf()) {
			Object instance = getInstance(node, builderClass);
			strings.add(tr("instance", instance));
		}

		strings.add(tr("count", count++));
		return "<table border=0>" + Joiner.on("").join(strings) + "</table>";
	}

	protected Optional<Class<Builder<?>>> getBuilderClass(Class<?> type) {
		Optional<?> builderClass = findPublicStaticBuilderClass(type);
		if (builderClass.isPresent()) {
			@SuppressWarnings("unchecked")
			Class<Builder<?>> builder = (Class<Builder<?>>) builderClass.get();
			return Optional.of(builder);
		} else {
			return absent();
		}
	}

	protected Object getInstance(Node<JsonDescriptor> node, Optional<Class<Builder<?>>> builderClass) {
		if (!builderClass.isPresent()) {
			return readValue(node.getElement().getType(), node.getElement().getNode());
		} else {
			if (node.getElement().getNode().isArray()) {
				return getCollection(node);
			} else {
				Builder<?> builder = (Builder<?>) readValue(builderClass.get(), node.getElement().getNode());
				return builder.build();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Collection getCollection(Node<JsonDescriptor> node) {
		JsonDescriptor desc = node.getElement();
		Class<?> type = desc.getType();
		Optional<Class<Builder<?>>> builderClass = getBuilderClass(type);
		if (builderClass.isPresent()) {
			String fieldName = desc.getDescriptor().get().getField().getName();
			Optional<Field> builderField = fromNullable(findField(builderClass.get(), fieldName));
			checkState(builderField.isPresent(), "builder field must be present");
			type = builderField.get().getType();
		}
		FieldDescriptor fd = desc.getDescriptor().get();
		List<JsonNode> children = newArrayList(desc.getNode().elements());
		Collection c = createCollection(type, children.size());
		for (JsonNode child : children) {
			Object instance = readValue(fd.getElementTypeDescriptor().getType(), child);
			c.add(instance);
		}
		return c;
	}

	protected Object readValue(Class<?> type, JsonNode node) {
		try {
			return mapper.readValue(node.toString(), type);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	protected Class<?> findTypeToCreate(JsonDescriptor desc) {
		JsonNode node = desc.getNode();
		if (node.isArray()) {
			checkState(desc.getDescriptor().isPresent(), "field descriptor must be present");
			FieldDescriptor fd = desc.getDescriptor().get();
			checkState(fd.getElementTypeDescriptor() != null, "element type descriptor must be present");
			return fd.getElementTypeDescriptor().getType();
		} else {
			return desc.getType();
		}
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
		return "<tr><td align=right>" + label + "</td><td>&nbsp;</td><td>" + value + "</td></tr>";
	}

}
