package org.kuali.common.devops.json.system;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.kuali.common.util.tree.Node;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.common.base.Function;

/**
 * Converts key/value pairs into correctly nested json. The pair {@code foo.bar=baz} would normally be translated to json as:
 * 
 * <pre>
 * {
 *   "foo.bar" : "baz"
 * }
 * </pre>
 * 
 * This serializer converts {@code foo.bar=baz} into json using the nested structure implied by the key:
 * 
 * <pre>
 * {
 *   "foo" : {
 *     "bar" : "baz"
 *   }
 * }
 * </pre>
 */
public class NestedPropertiesSerializer extends StdSerializer<Properties> {

	private final String separator = ".";
	private final Function<Set<String>, Node<String>> keys = new NestedKeysFunction(separator);

	public NestedPropertiesSerializer(Class<Properties> type) {
		super(type);
	}

	@Override
	public void serialize(Properties properties, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
		Function<Node<String>, ObjectNode> nodes = new JsonNodeFunction(separator, properties);
		Node<String> node = keys.apply(properties.stringPropertyNames());
		JsonNode jsonNode = nodes.apply(node);
		jgen.writeObject(jsonNode);
	}

}
