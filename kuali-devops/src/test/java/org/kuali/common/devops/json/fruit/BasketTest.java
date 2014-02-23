package org.kuali.common.devops.json.fruit;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.kuali.common.util.ReflectionUtils.copyProperty;
import static org.kuali.common.util.ReflectionUtils.newInstance;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.build.BuilderUtils.declaresPublicStaticBuilderClass;
import static org.kuali.common.util.build.BuilderUtils.findPublicStaticBuilderClass;
import static org.springframework.util.ReflectionUtils.findField;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.Builder;
import org.junit.Test;
import org.springframework.core.convert.TypeDescriptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class BasketTest {

	@Test
	public void test() {
		try {
			Apple a1 = Apple.builder().color("red").build();
			Apple a2 = Apple.builder().color("green").build();
			List<Apple> apples = ImmutableList.of(a1, a2);
			Basket basket = Basket.builder().apples(apples).build();
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(basket);
			JsonNode node = mapper.readTree(json);
			print(node);
			System.out.println(json);
			Basket newBasket = create(basket.getClass(), mapper, json);
			System.out.println(mapper.writeValueAsString(newBasket));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected static <T> T create(Class<T> type, ObjectMapper mapper, String json) {
		JsonNode node = readTree(mapper, json);
		return build(type, mapper, node, Optional.<Field> absent());
	}

	protected static <T> T build(Class<T> type, ObjectMapper mapper, JsonNode node, Optional<Field> field) {
		if (node.isContainerNode()) {
			// This node is JSON text that represents a nested object reference
			// Recursing down the tree will correctly parse the JSON text into an object
			return recurse(mapper, node, type, field);
		} else {
			// This node is JSON text that is a "leaf" node
			// It can be parsed directly into a Java object
			return builderSensitiveRead(mapper, node, type);
		}
	}

	protected static <T> T recurse(ObjectMapper mapper, JsonNode node, Class<T> type, Optional<Field> field) {

		// If we are recursing, the node must be a container node
		checkState(node.isContainerNode(), "[%s] is not a container node", node);

		// Handle JSON array's
		if (node.isArray()) {
			return doArray(field, node);
		}

		// Determine if this type contains a builder
		Optional<Class<Builder<T>>> builderClass = findPublicStaticBuilderClass(type);
		if (builderClass.isPresent()) {
			// If so, parse the JSON into a builder object, then invoke it's build() method to produce an instance
			return createBuilder(mapper, node, builderClass.get(), field).build();
		} else {
			// Otherwise, parse the JSON into an object directly
			return read(mapper, node, type);
		}
	}

	protected static <T> T doArray(Optional<Field> field, JsonNode node) {
		checkState(field.isPresent(), "field is required for arrays");
		TypeDescriptor descriptor = new TypeDescriptor(field.get());
		Optional<TypeDescriptor> etd = fromNullable(descriptor.getElementTypeDescriptor());
		if (etd.isPresent()) {
			Class<?> etdType = etd.get().getType();
			if (declaresPublicStaticBuilderClass(etdType)) {

			}
			Optional<Class<Builder<T>>> builderClass = null; // findPublicStaticBuilderClass(etdType);
			for (String fieldName : newArrayList(node.fieldNames())) {

			}
		}
		return null;
	}

	protected static <T> Builder<T> createBuilder(ObjectMapper mapper, JsonNode node, Class<Builder<T>> builderClass, Optional<Field> field) {
		Map<String, Object> fields = createFields(mapper, node, builderClass);
		Builder<T> builder = newInstance(builderClass);
		for (String fieldName : fields.keySet()) {
			copyProperty(builder, fieldName, fields.get(field));
		}
		return builder;
	}

	protected static <T> Map<String, Object> createFields(ObjectMapper mapper, JsonNode node, Class<Builder<T>> builderClass) {
		Map<String, Object> fields = newHashMap();
		for (String fieldName : newArrayList(node.fieldNames())) {
			Optional<JsonNode> child = fromNullable(node.get(fieldName));
			checkState(child.isPresent(), "node does not contain %s", fieldName);
			Optional<Field> childField = fromNullable(findField(builderClass, fieldName));
			checkState(childField.isPresent(), "[%s] does not contain field [%s]", builderClass.getCanonicalName(), fieldName);
			Object instance = build(childField.get().getType(), mapper, child.get(), childField);
			fields.put(fieldName, instance);
		}
		return fields;
	}

	protected static <T> T builderSensitiveRead(ObjectMapper mapper, JsonNode node, Class<T> type) {
		Optional<Class<Builder<T>>> builderClass = findPublicStaticBuilderClass(type);
		if (builderClass.isPresent()) {
			// Parse the JSON into an instance of a Builder class
			// Invoke it's build() method to produce an object instance
			return read(mapper, node, builderClass.get()).build();
		} else {
			// Parse the JSON directly into an object instance
			return read(mapper, node, type);
		}
	}

	protected static <T> T read(ObjectMapper mapper, JsonNode node, Class<T> type) {
		try {
			return mapper.readValue(node.toString(), type);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	protected static JsonNode readTree(ObjectMapper mapper, String json) {
		try {
			return mapper.readTree(json);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	protected void print(JsonNode node) {
		print(node, 0);
	}

	protected void print(final JsonNode node, final int indent) {
		System.out.println(repeat(" ", indent) + toString(node));
		int indentation = indent + 2;
		for (JsonNode element : newArrayList(node.iterator())) {
			print(element, indentation);
		}
	}

	protected String toString(JsonNode node) {
		List<String> strings = newArrayList();
		strings.add("container=" + node.isContainerNode() + "");
		strings.add("value=" + node.isValueNode() + "");
		strings.add("array=" + node.isArray());
		List<String> fields = newArrayList(node.fieldNames());
		if (!fields.isEmpty()) {
			strings.add("fields=" + Joiner.on(',').join(fields));
		} else {
			strings.add("fields=none");
		}
		return Joiner.on("::").join(strings);
	}

}
