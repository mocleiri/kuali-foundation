package org.kuali.common.devops.json.breakfast;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.kuali.common.util.ReflectionUtils.copyProperty;
import static org.kuali.common.util.ReflectionUtils.newInstance;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.build.BuilderUtils.findPublicStaticBuilderClass;
import static org.springframework.util.ReflectionUtils.findField;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.Builder;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class SuperFancyBowlTest {

	@Test
	public void test() {
		try {
			List<String> ingredients = ImmutableList.of("sodium", "potassium");
			Map<String, String> nutritionFacts = ImmutableMap.of("calories", "220", "fat", "2g");
			FancyMilk milk = FancyMilk.builder().price(2.29).type("lowfat").ingredients(ingredients).nutritionFacts(nutritionFacts).build();
			SuperFancyBowl bowl = SuperFancyBowl.builder().milk(milk).build();
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(bowl);
			System.out.println(json);
			SuperFancyBowl newBowl = build(bowl.getClass(), mapper, json);
			System.out.println(mapper.writeValueAsString(newBowl));
			JsonNode node = mapper.readTree(json);
			print(node);
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
		if (node.isArray()) {
			System.out.println("yo");
		}
		if (builderClass.isPresent()) {
			if (node.isContainerNode()) {
				return recurse(mapper, node, builderClass.get()).build();
			} else {
				return readValue(mapper, node, builderClass.get()).build();
			}
		} else {
			return readValue(mapper, node, type);
		}
	}

	protected static <T> Builder<T> recurse(ObjectMapper mapper, JsonNode node, Class<Builder<T>> builderClass) {
		Map<String, Object> fields = newHashMap();
		for (String fieldName : newArrayList(node.fieldNames())) {
			Optional<JsonNode> child = fromNullable(node.get(fieldName));
			checkState(child.isPresent(), "node does not contain %s", fieldName);
			Optional<Field> field = fromNullable(findField(builderClass, fieldName));
			checkState(field.isPresent(), "[%s] does not contain field [%s]", builderClass.getCanonicalName(), fieldName);
			Object instance = build(field.get().getType(), mapper, child.get());
			fields.put(fieldName, instance);
		}
		Builder<T> builder = newInstance(builderClass);
		for (String field : fields.keySet()) {
			copyProperty(builder, field, fields.get(field));
		}
		return builder;
	}

	protected static <T> T readValue(ObjectMapper mapper, JsonNode node, Class<T> type) {
		try {
			String json = mapper.writeValueAsString(node);
			return mapper.readValue(json, type);
		} catch (JsonProcessingException e) {
			throw illegalState(e);
		} catch (IOException e) {
			throw illegalState(e);
		}
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
		List<String> fields = newArrayList(node.fieldNames());
		if (!fields.isEmpty()) {
			strings.add("fields=" + Joiner.on(',').join(fields));
		} else {
			strings.add("fields=none");
		}
		return Joiner.on("::").join(strings);
	}

}
