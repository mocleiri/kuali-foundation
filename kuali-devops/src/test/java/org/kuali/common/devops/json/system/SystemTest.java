package org.kuali.common.devops.json.system;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Maps.newTreeMap;
import static com.google.common.collect.Sets.newTreeSet;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

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
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;

public class SystemTest {

	@Test
	public void test() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
			JacksonContext context = JacksonContext.builder().mapper(mapper).build();
			JsonService service = new JacksonJsonService(context);
			List<String> keys = PropertyUtils.getStartsWithKeys(System.getProperties(), "os.");
			SortedMap<String, String> map = newTreeMap();
			for (String key : keys) {
				map.put(key, System.getProperty(key));
			}
			OperatingSystem os = OperatingSystem.builder().withArchitecture("x86_64").withName("Mac OS X").withVersion("10.8.5").build();
			JVM jvm = JVM.builder().withOperatingSystem(os).build();
			JsonNode root = manual();
			String json = service.writeString(jvm);
			System.out.println(service.writeString(jvm));
			System.out.println(service.writeString(map));
			System.out.println(service.writeString(root));
			JVM jvm2 = service.readString(json, JVM.class);
			Node<String> tree = buildTree(keys);
			String html = Trees.html(tree);
			FileUtils.write(new File("/tmp/system.htm"), html);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected Node<String> buildTree(Collection<String> keys) {
		MutableNode<String> root = MutableNode.of("system");
		Map<String, MutableNode<String>> map = newHashMap();
		for (String key : keys) {
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
