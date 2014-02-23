package org.kuali.common.devops.json.fruit;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.repeat;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.tree.Node;
import org.springframework.core.convert.TypeDescriptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
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
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected Node<JsonDescriptor> buildTree(Class<?> type, JsonNode node) {
		TypeDescriptor descriptor = new TypeDescriptor((Field) null);
		return null;
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
