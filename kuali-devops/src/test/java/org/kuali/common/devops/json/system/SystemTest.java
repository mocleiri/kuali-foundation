package org.kuali.common.devops.json.system;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.google.common.collect.Sets.newTreeSet;
import static org.kuali.common.util.PropertyUtils.newHashMap;

import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.devops.json.pojo.JacksonContext;
import org.kuali.common.devops.json.pojo.JacksonJsonService;
import org.kuali.common.devops.json.pojo.JsonService;
import org.kuali.common.util.system.VirtualSystem;
import org.kuali.common.util.tree.Node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class SystemTest {

	private static final JsonNodeFactory FACTORY = new JsonNodeFactory(false);
	private static final String PROPERTIES = "properties";
	private static final String ENVIRONMENT = "environment";
	private static final String SEPARATOR = ".";

	@Test
	public void test() {
		try {
			JsonService service = getService();
			JsonNode jsonNode = getSystemNode();
			String json = service.writeString(jsonNode);
			VirtualSystem vs = service.readString(json, VirtualSystem.class);
			System.out.println(service.writeString(vs));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected JsonNode getSystemNode() {
		Properties system = System.getProperties();
		Properties props = new SystemPropertiesFunction().apply(system);
		Node<String> node = new NestedKeysFunction(SEPARATOR).apply(props.stringPropertyNames());
		ObjectNode objectNode = new JsonNodeFunction(SEPARATOR, props).apply(node);
		objectNode.put(PROPERTIES, getObjectNode(system));
		objectNode.put(ENVIRONMENT, getObjectNode(System.getenv()));
		return objectNode;
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

	protected JsonService getService() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
		JacksonContext context = JacksonContext.builder().withMapper(mapper).build();
		return new JacksonJsonService(context);
	}

}
