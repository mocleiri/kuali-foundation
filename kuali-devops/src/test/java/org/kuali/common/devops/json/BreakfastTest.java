package org.kuali.common.devops.json;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.google.common.base.Joiner;

public class BreakfastTest {

	@Test
	public void test() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Milk milk = Milk.builder().price(2.29).type("lowfat").build();
			Bowl bowl = Bowl.builder().milk(milk).build();
			String json = mapper.writeValueAsString(bowl);
			System.out.println(json);
			JsonNode node = mapper.readTree(json);
			print(node);
		} catch (Throwable e) {
			e.printStackTrace();
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
			strings.add("field=none");
		}
		return Joiner.on("::").join(strings);
	}

}
