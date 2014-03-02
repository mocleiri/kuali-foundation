package org.kuali.common.core.system;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.newTreeSet;
import static org.kuali.common.core.json.jackson.JacksonContext.newDefaultObjectMapper;
import static org.kuali.common.core.system.VirtualSystemPropertiesFunction.newVirtualSystemPropertiesFunction;
import static org.kuali.common.util.PropertyUtils.newHashMap;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.kuali.common.core.json.api.JsonService;
import org.kuali.common.core.json.jackson.JacksonContext;
import org.kuali.common.core.json.jackson.JacksonJsonService;
import org.kuali.common.core.json.jackson.JsonNodeFunction;
import org.kuali.common.core.json.jackson.NestedKeysFunction;
import org.kuali.common.core.json.jackson.PathDeserializer;
import org.kuali.common.util.base.string.SplitterFunction;
import org.kuali.common.util.tree.Node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSet;

public class VirtualSystemHelper {

	private static final JsonNodeFactory FACTORY = new JsonNodeFactory(false);
	private static final String PROPERTIES = "properties";
	private static final String ENVIRONMENT = "environment";
	private static final String SEPARATOR = ".";

	/**
	 * System property keys that are required to be present on every JVM
	 */
	public static final ImmutableSet<String> DEFAULT_REQUIRED_SYSTEM_PROPERTY_KEYS = getRequiredPropertyKeys();

	/**
	 * Mappings between required system property keys and the strongly typed field they correspond to in the VirtualSystem object
	 */
	public static final ImmutableBiMap<String, String> DEFAULT_REQUIRED_SYSTEM_PROPERTY_KEY_MAPPINGS = getPropertyMappings();

	/**
	 * Produce an immutable {@code VirtualSystem} representing the current state of the system we are running on.
	 */
	public static VirtualSystem createVirtualSystem() {
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

	private static final ImmutableBiMap<String, String> getPropertyMappings() {
		BiMap<String, String> mappings = HashBiMap.create();
		mappings.put("path.separator", "pathSeparator");
		mappings.put("file.separator", "fileSeparator");
		mappings.put("line.separator", "lineSeparator");
		mappings.put("java.class.path", "java.classpath");
		mappings.put("java.class.version", "java.classVersion");
		mappings.put("java.io.tmpdir", "java.tmpDir");
		mappings.put("java.ext.dirs", "java.extensionDirs");
		mappings.put("java.library.path", "java.libraryPaths");
		mappings.put("java.version", "java.runtime.version");
		mappings.put("java.vendor", "java.runtime.vendor");
		mappings.put("java.vendor.url", "java.runtime.vendorUrl");
		mappings.put("java.specification.version", "java.runtime.specification.version");
		mappings.put("java.specification.vendor", "java.runtime.specification.vendor");
		mappings.put("java.specification.name", "java.runtime.specification.name");

		Set<String> required = getRequiredPropertyKeys();
		for (String key : mappings.keySet()) {
			checkArgument(required.contains(key), "unknown key [%s]", key);
		}
		return ImmutableBiMap.copyOf(mappings);

	}

	private static final ImmutableSet<String> getRequiredPropertyKeys() {
		Set<String> keys = newHashSet();
		keys.add("java.version");
		keys.add("java.vendor");
		keys.add("java.vendor.url");
		keys.add("java.home");
		keys.add("java.vm.specification.version");
		keys.add("java.vm.specification.vendor");
		keys.add("java.vm.specification.name");
		keys.add("java.vm.version");
		keys.add("java.vm.vendor");
		keys.add("java.vm.name");
		keys.add("java.specification.version");
		keys.add("java.specification.vendor");
		keys.add("java.specification.name");
		keys.add("java.class.version");
		keys.add("java.class.path");
		keys.add("java.library.path");
		keys.add("java.io.tmpdir");
		// keys.add("java.compiler"); javadoc states this is required, but it sure isn't
		keys.add("java.ext.dirs");
		keys.add("os.name");
		keys.add("os.arch");
		keys.add("os.version");
		keys.add("file.separator");
		keys.add("path.separator");
		keys.add("line.separator");
		keys.add("user.name");
		keys.add("user.home");
		keys.add("user.dir");
		return ImmutableSet.copyOf(keys);
	}

}
