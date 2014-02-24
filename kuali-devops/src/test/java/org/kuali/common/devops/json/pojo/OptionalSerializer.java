package org.kuali.common.devops.json.pojo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.base.Optional;

public class OptionalSerializer extends JsonSerializer<Optional<?>> {

	@Override
	public void serialize(Optional<?> value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeStartObject();
		jgen.writeObjectField("optional", "foo");
		jgen.writeEndObject();
	}

}
