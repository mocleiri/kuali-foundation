package org.kuali.common.devops.json.fruit;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.springframework.util.ReflectionUtils.findField;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.tree.ImmutableNode;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class BasketTest2 {

	@Test
	public void test() {
		try {
			Apple a1 = Apple.builder().color("red").build();
			Apple a2 = Apple.builder().color("green").build();
			List<Apple> apples = ImmutableList.of(a1, a2);
			Basket basket = Basket.builder().apples(apples).material("straw").build();
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(basket);
			JsonNode node = mapper.readTree(json);
			print(node);
			System.out.println(json);
			Node<JsonDescriptor> tree = ImmutableNode.copyOf(buildTree(Basket.class, node));
			System.out.println(tree);
			System.out.println(mapper.writeValueAsString(tree));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected MutableNode<JsonDescriptor> buildTree(Class<?> type, JsonNode node) {
		return buildTree(type, Optional.<FieldDescriptor> absent(), node);
	}

	protected MutableNode<JsonDescriptor> buildTree(Class<?> type, Optional<FieldDescriptor> descriptor, JsonNode node) {
		JsonDescriptor jd = JsonDescriptor.builder().type(type).descriptor(descriptor).node(node).build();
		MutableNode<JsonDescriptor> mutable = MutableNode.of(jd);
		for (JsonDescriptor child : getChildren(type, node)) {
			MutableNode<JsonDescriptor> childNode = buildTree(child.getType(), child.getDescriptor(), child.getNode());
			mutable.add(childNode);
		}
		return mutable;
	}

	protected List<JsonDescriptor> getChildren(Class<?> type, JsonNode json) {
		List<JsonDescriptor> children = newArrayList();
		for (String fieldName : newArrayList(json.fieldNames())) {
			Optional<JsonNode> node = fromNullable(json.get(fieldName));
			Optional<Field> field = fromNullable(findField(type, fieldName));
			checkState(node.isPresent(), "[%s] does not contain field '%s'", json, fieldName);
			checkState(field.isPresent(), "[%s] does not contain field '%s'", type.getCanonicalName(), fieldName);
			FieldDescriptor descriptor = new FieldDescriptor(field.get());
			JsonDescriptor child = JsonDescriptor.builder().type(field.get().getType()).descriptor(descriptor).node(node.get()).build();
			children.add(child);
		}
		return children;
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
		return Joiner.on(" :: ").join(strings);
	}

}
