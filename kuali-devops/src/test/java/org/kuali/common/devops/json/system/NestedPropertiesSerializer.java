package org.kuali.common.devops.json.system;

import java.io.IOException;
import java.util.Properties;

import org.kuali.common.util.tree.Node;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
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

	private final Function<Properties, Node<String>> function1 = new NestedPropertiesFunction();
	private final Function<Node<String>, JsonNode> function2 = new JsonNodeFunction();

	public NestedPropertiesSerializer(Class<Properties> type) {
		super(type);
	}

	@Override
	public void serialize(Properties properties, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
		Node<String> node = function1.apply(properties);
		JsonNode jsonNode = function2.apply(node);
		jgen.writeObject(jsonNode);
	}

}
