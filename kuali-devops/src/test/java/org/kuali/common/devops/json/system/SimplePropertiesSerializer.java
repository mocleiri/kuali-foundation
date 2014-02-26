package org.kuali.common.devops.json.system;

import static com.google.common.collect.Sets.newTreeSet;

import java.io.IOException;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class SimplePropertiesSerializer extends StdSerializer<Properties> {

	public SimplePropertiesSerializer(JavaType type) {
		super(type);
	}

	@Override
	public void serialize(Properties props, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
		jgen.writeStartObject();
		for (String key : newTreeSet(props.stringPropertyNames())) {
			jgen.writeStringField(key, props.getProperty(key));
		}
		jgen.writeEndObject();
	}

}
