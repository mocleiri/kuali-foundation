package org.kuali.common.devops.json.fruit;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.log.Loggers.newLogger;
import static org.springframework.util.ReflectionUtils.findField;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.tree.ImmutableNode;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;
import org.kuali.common.util.tree.Trees;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class BasketTest2 {

	private static final Logger logger = newLogger();

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
			Node<JsonDescriptor> tree = ImmutableNode.copyOf(buildTree(Basket.class, node));
			String html = Trees.html(tree, new JsonHtmlFunction());
			write("/tmp/json.htm", html);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected void write(String file, String content) {
		try {
			File f = new CanonicalFile(file);
			logger.info(format("creating -> %s", f));
			FileUtils.write(f, content);
		} catch (IOException e) {
			throw illegalState(e);
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

}
