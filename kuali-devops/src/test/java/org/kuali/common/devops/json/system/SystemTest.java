package org.kuali.common.devops.json.system;

import static com.google.common.collect.Sets.newTreeSet;
import static org.kuali.common.util.PropertyUtils.newHashMap;
import static org.kuali.common.util.system.VirtualSystem.MAPPED_SYSTEM_PROPERTIES;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.devops.json.pojo.JacksonContext;
import org.kuali.common.devops.json.pojo.JacksonJsonService;
import org.kuali.common.devops.json.pojo.JsonService;
import org.kuali.common.util.system.Java;
import org.kuali.common.util.system.PathDeserializer;
import org.kuali.common.util.system.VirtualSystem;
import org.kuali.common.util.tree.Node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
			// This json still represents classpath as a single string delimited with ":" (vs an array of strings)
			String json1 = service.writeString(jsonNode);
			// This service parses the delimited string into a list of File objects
			VirtualSystem vs1 = getSystemPropertyDeserializerService().readString(json1, VirtualSystem.class);
			// This json represents classpath as an array of strings (vs a single string delimited with ":")
			String json2 = service.writeString(vs1);
			System.out.println(json2);
			VirtualSystem vs2 = service.readString(json2, VirtualSystem.class);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected JsonNode getSystemNode() {
		Node<String> node = new NestedKeysFunction(SEPARATOR).apply(MAPPED_SYSTEM_PROPERTIES.stringPropertyNames());
		ObjectNode objectNode = new JsonNodeFunction(SEPARATOR, MAPPED_SYSTEM_PROPERTIES).apply(node);
		objectNode.put(PROPERTIES, getObjectNode(System.getProperties()));
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

	protected JsonService getSystemPropertyDeserializerService() {
		ObjectMapper mapper = JacksonContext.getNewDefaultObjectMapper();
		mapper.addMixInAnnotations(Java.Builder.class, SystemPropertyPathDeserializer.class);
		JacksonContext context = JacksonContext.builder().withMapper(mapper).build();
		return new JacksonJsonService(context);
	}

	protected JsonService getService() {
		JacksonContext context = JacksonContext.builder().withPrettyPrint(true).build();
		return new JacksonJsonService(context);
	}

	private static final class SystemPropertyPathDeserializer {

		@JsonDeserialize(using = PathDeserializer.class)
		private List<File> classpath;

		@JsonDeserialize(using = PathDeserializer.class)
		private List<File> libraryPaths;

		@JsonDeserialize(using = PathDeserializer.class)
		private List<File> extensionDirs;

	}

}
