package org.kuali.common.devops.json.system;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.google.common.collect.Sets.newTreeSet;
import static org.kuali.common.util.PropertyUtils.newHashMap;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.devops.json.pojo.JacksonContext;
import org.kuali.common.devops.json.pojo.JacksonJsonService;
import org.kuali.common.devops.json.pojo.JsonService;
import org.kuali.common.util.system.VirtualSystem;
import org.kuali.common.util.tree.Node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableList;

public class SystemTest {

	private static final JsonNodeFactory FACTORY = new JsonNodeFactory(false);

	@Test
	public void test() {
		try {
			String separator = ".";
			Properties props = new SystemPropertiesFunction().apply(System.getProperties());
			Node<String> node = new NestedKeysFunction(separator).apply(props.stringPropertyNames());
			ObjectNode objectNode = new JsonNodeFunction(separator, props).apply(node);
			objectNode.put("properties", getObjectNode(System.getProperties()));
			objectNode.put("environment", getObjectNode(System.getenv()));

			JsonService service = getJsonService();
			String json = service.writeString(objectNode);
			VirtualSystem vs = service.readString(json, VirtualSystem.class);
			JsonService simple = getSimpleJsonService();
			System.out.println(simple.writeString(vs));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected JsonNode getObjectNode(Properties props) {
		return getObjectNode(newHashMap(props));
	}

	protected ObjectNode getObjectNode(Map<String, String> props) {
		ObjectNode objectNode = new ObjectNode(FACTORY);
		for (String key : newTreeSet(props.keySet())) {
			TextNode textNode = new TextNode(props.get(key));
			objectNode.put(key, textNode);
		}
		return objectNode;
	}

	protected JsonService getSimpleJsonService() {
		ObjectMapper mapper = new ObjectMapper();
		List<Module> modules = ImmutableList.of(new GuavaModule(), getSimpleModule());
		JacksonContext context = JacksonContext.builder().withModules(modules).withMapper(mapper).build();
		return new JacksonJsonService(context);
	}

	protected JsonService getJsonService() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<Module> modules = ImmutableList.of(new GuavaModule(), getModule());
		JacksonContext context = JacksonContext.builder().withModules(modules).withMapper(mapper).build();
		return new JacksonJsonService(context);
	}

	protected Module getSimpleModule() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(new SortedPropertiesSerializer(Properties.class));
		return module;
	}

	protected Module getModule() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(new NestedPropertiesSerializer(Properties.class));
		return module;
	}
}
