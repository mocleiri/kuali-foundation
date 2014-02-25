package org.kuali.common.devops.json.system;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newTreeSet;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.devops.json.pojo.JacksonContext;
import org.kuali.common.devops.json.pojo.JacksonJsonService;
import org.kuali.common.devops.json.pojo.JsonService;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;
import org.kuali.common.util.tree.Trees;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;

public class SystemTest {

	private static final JsonNodeFactory FACTORY = new JsonNodeFactory(true);

	@Test
	public void test() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
			JacksonContext context = JacksonContext.builder().mapper(mapper).build();
			JsonService service = new JacksonJsonService(context);
			List<String> keys = PropertyUtils.getStartsWithKeys(System.getProperties(), "java.");
			OperatingSystem os = OperatingSystem.builder().withArchitecture("x86_64").withName("Mac OS X").withVersion("10.8.5").build();
			JVM jvm1 = JVM.builder().withOperatingSystem(os).build();
			JsonNode root = manual();
			String json = service.writeString(root);
			// System.out.println(json);
			// System.out.println(service.writeString(root));
			JVM jvm2 = service.readString(json, JVM.class);
			Properties duplicate = PropertyUtils.duplicate(System.getProperties());
			PropertyUtils.trim(duplicate, keys, null);
			Node<String> tree = buildTree(duplicate);
			String html = Trees.html(tree);
			FileUtils.write(new File("/tmp/system.htm"), html);
			JsonNode jsonNode = buildJson(tree, duplicate);
			String propsJson = service.writeString(jsonNode);
			FileUtils.write(new File("/tmp/system.json"), propsJson);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected JsonNode buildJson(Node<String> node, Properties properties) {
		if (node.isLeaf()) {
			List<String> tokens = node.getElementPath();
			tokens.remove(0);
			String key = Joiner.on('.').join(tokens);
			String value = properties.getProperty(key);
			return new TextNode(value);
		} else {
			ObjectNode objectNode = new ObjectNode(FACTORY);
			for (Node<String> child : node.getChildren()) {
				objectNode.put(child.getElement(), buildJson(child, properties));
			}
			return objectNode;
		}
	}

	protected Node<String> buildTree(Properties properties) {
		MutableNode<String> root = MutableNode.of("root");
		Map<String, MutableNode<String>> map = newHashMap();
		for (String key : properties.stringPropertyNames()) {
			addNodes(map, key);
		}
		for (String key : newTreeSet(map.keySet())) {
			MutableNode<String> child = map.get(key);
			String parentKey = getParentKey(key);
			Optional<MutableNode<String>> parent = fromNullable(map.get(parentKey));
			if (parent.isPresent()) {
				parent.get().add(child);
			} else {
				root.add(child);
			}
		}
		return root;
	}

	protected String getParentKey(String key) {
		List<String> tokens = Splitter.on('.').splitToList(key);
		List<String> list = newArrayList();
		for (int i = 0; i < tokens.size() - 1; i++) {
			list.add(tokens.get(i));
		}
		return Joiner.on('.').join(list);
	}

	protected void addNodes(Map<String, MutableNode<String>> map, String key) {
		List<String> paths = getPaths(key);
		for (String path : paths) {
			MutableNode<String> node = map.get(path);
			if (node == null) {
				List<String> tokens = Splitter.on('.').splitToList(path);
				String token = tokens.get(tokens.size() - 1);
				node = MutableNode.of(token);
				map.put(path, node);
			}
		}
	}

	protected List<String> getPaths(String key) {
		List<String> tokens = Splitter.on('.').splitToList(key);
		List<String> paths = newArrayList();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tokens.size(); i++) {
			if (i != 0) {
				sb.append('.');
			}
			String token = tokens.get(i);
			paths.add(sb.append(token).toString());
		}
		return paths;
	}

	protected JsonNode manual() {
		JsonNodeFactory jnf = new JsonNodeFactory(true);

		ObjectNode os = new ObjectNode(jnf);
		os.put("arch", "x86_64");
		os.put("name", "Mac OS X");
		os.put("version", "10.8.5");

		ObjectNode root = new ObjectNode(jnf);
		root.put("os", os);
		return root;
	}

}
