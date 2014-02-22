package org.kuali.common.devops.json;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.kuali.common.util.base.Exceptions.illegalState;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class BreakfastTest {

	@Test
	public void test() {
		try {
			List<String> ingredients = ImmutableList.of("sodium", "potassium");
			Map<String, String> nutritionFacts = ImmutableMap.of("calories", "220", "fat", "2g");
			Milk milk = Milk.builder().price(2.29).type("lowfat").ingredients(ingredients).nutritionFacts(nutritionFacts).build();
			Bowl bowl = Bowl.builder().milk(milk).build();
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(bowl);
			System.out.println(json);
			JsonNode node = mapper.readTree(json);
			print(node);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected static <T> T read(Class<T> type, ObjectMapper mapper, String json) {
		JsonNode node = readTree(mapper, json);
		return null;
	}

	protected static JsonNode readTree(ObjectMapper mapper, String json) {
		try {
			return mapper.readTree(json);
		} catch (JsonProcessingException e) {
			throw illegalState(e);
		} catch (IOException e) {
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
		List<String> fields = newArrayList(node.getFieldNames());
		if (!fields.isEmpty()) {
			strings.add("fields=" + Joiner.on(',').join(node.getFieldNames()));
		} else {
			strings.add("fields=none");
		}
		return Joiner.on("::").join(strings);
	}

}
