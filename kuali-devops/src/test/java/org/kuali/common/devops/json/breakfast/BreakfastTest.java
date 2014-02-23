package org.kuali.common.devops.json.breakfast;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.build.BuilderUtils.findPublicStaticBuilderClass;

import java.util.List;

import org.apache.commons.lang3.builder.Builder;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;

public class BreakfastTest {

	@Test
	public void test() {
		try {
			Bowl bowl = Bowl.builder().depth(3.0).width(5.0).build();
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(bowl);
			System.out.println(json);
			JsonNode node = mapper.readTree(json);
			print(node);
			Bowl newBowl = build(Bowl.class, mapper, json);
			System.out.println(mapper.writeValueAsString(newBowl));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected static <T> T build(Class<T> type, ObjectMapper mapper, String json) {
		JsonNode node = readTree(mapper, json);
		return build(type, mapper, node);
	}

	protected static <T> T build(Class<T> type, ObjectMapper mapper, JsonNode node) {
		Optional<Class<Builder<T>>> builderClass = findPublicStaticBuilderClass(type);
		if (builderClass.isPresent()) {
			return readValue(mapper, node, builderClass.get()).build();
		} else {
			return readValue(mapper, node, type);
		}
	}

	protected static <T> T readValue(ObjectMapper mapper, JsonNode node, Class<T> type) {
		try {
			String json = mapper.writeValueAsString(node);
			return mapper.readValue(json, type);
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
