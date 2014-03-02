package org.kuali.common.util.system;

import static com.google.common.collect.Sets.newTreeSet;
import static org.kuali.common.util.PropertyUtils.newHashMap;
import static org.kuali.common.util.json.jackson.JacksonContext.newDefaultObjectMapper;
import static org.kuali.common.util.system.VirtualSystemPropertiesFunction.newVirtualSystemPropertiesFunction;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.kuali.common.util.base.string.SplitterFunction;
import org.kuali.common.util.json.api.JsonService;
import org.kuali.common.util.json.jackson.JacksonContext;
import org.kuali.common.util.json.jackson.JacksonJsonService;
import org.kuali.common.util.json.jackson.JsonNodeFunction;
import org.kuali.common.util.json.jackson.NestedKeysFunction;
import org.kuali.common.util.json.jackson.PathDeserializer;
import org.kuali.common.util.tree.Node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class VirtualSystemFactory {

	private static final JsonNodeFactory FACTORY = new JsonNodeFactory(false);
	private static final String PROPERTIES = "properties";
	private static final String ENVIRONMENT = "environment";
	private static final String SEPARATOR = ".";

	/**
	 * Produce an immutable {@code VirtualSystem} representing the current state of the system we are running on.
	 */
	public static VirtualSystem newVirtualSystem() {
		// Get a handle to our customized json service
		JsonService service = newCustomJsonService();

		// Create a json node representing the current state of the system we are running on
		JsonNode jsonNode = newVirtualSystemJsonNode();

		// This json represents java.class.path, java.library.path, and java.ext.dirs as delimited strings
		String json = service.writeString(jsonNode);

		// This service contains a custom deserializer mixin that parses delimited strings into List<File>
		return service.readString(json, VirtualSystem.class);
	}

	protected static JsonNode newVirtualSystemJsonNode() {
		Properties system = System.getProperties();
		Properties mapped = newVirtualSystemPropertiesFunction().apply(system);
		Set<String> paths = new SplitterFunction(SEPARATOR).apply(mapped.stringPropertyNames());
		Node<String> node = new NestedKeysFunction(SEPARATOR).apply(paths);
		ObjectNode objectNode = new JsonNodeFunction(SEPARATOR, mapped).apply(node);
		objectNode.put(PROPERTIES, getObjectNode(newHashMap(system)));
		objectNode.put(ENVIRONMENT, getObjectNode(System.getenv()));
		return objectNode;
	}

	protected static ObjectNode getObjectNode(Map<String, String> properties) {
		ObjectNode objectNode = new ObjectNode(FACTORY);
		for (String key : newTreeSet(properties.keySet())) {
			objectNode.put(key, new TextNode(properties.get(key)));
		}
		return objectNode;
	}

	protected static JsonService newCustomJsonService() {
		ObjectMapper mapper = newDefaultObjectMapper();
		mapper.addMixInAnnotations(Java.Builder.class, SystemPropertyPathDeserializer.class);
		JacksonContext context = JacksonContext.builder().withMapper(mapper).build();
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
