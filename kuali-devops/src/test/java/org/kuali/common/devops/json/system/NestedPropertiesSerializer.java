package org.kuali.common.devops.json.system;

import java.io.IOException;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Converts key/value pairs into correctly nested json. The pair {@code foo.bar=baz} would normally be translated to json like this:
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

	private final NestedPropertiesNodeSupplier supplier = new NestedPropertiesNodeSupplier();

	public NestedPropertiesSerializer(Class<Properties> type) {
		super(type);
	}

	@Override
	public void serialize(Properties properties, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
		JsonNode jsonNode = supplier.getJsonNode(properties);
		jgen.writeObject(jsonNode);
	}

}
