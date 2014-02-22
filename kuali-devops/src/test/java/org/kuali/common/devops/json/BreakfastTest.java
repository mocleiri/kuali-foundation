package org.kuali.common.devops.json;

import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.base.Exceptions.illegalState;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.builder.Builder;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.google.common.base.Joiner;

public class BreakfastTest {

	@Test
	public void test() {
		try {
			Milk milk = Milk.builder().price(2.29).type("low\"fat").build();
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
	
	protected static <T> Builder<T> getBuilder(Class<T> type) {
		
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
		System.out.println(toString(node));
		for (JsonNode element : newArrayList(node.iterator())) {
			print(element);
		}
	}

	protected String toString(JsonNode node) {
		List<String> strings = newArrayList();
		strings.add("container=" + node.isContainerNode() + "");
		strings.add("value=" + node.isValueNode() + "");
		List<String> fields = newArrayList(node.getFieldNames());
		if (!fields.isEmpty()) {
			strings.add("fields=" + Joiner.on(',').join(node.getFieldNames()));
		} else {
			strings.add("fields=none");
		}
		return Joiner.on("::").join(strings);
	}

}
