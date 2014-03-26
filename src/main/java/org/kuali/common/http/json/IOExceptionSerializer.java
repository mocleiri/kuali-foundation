package org.kuali.common.http.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.base.Optional;

public class IOExceptionSerializer extends JsonSerializer<Optional<IOException>> {

	private static final String MESSAGE = "message";

	@Override
	public void serialize(Optional<IOException> exception, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		if (exception.isPresent()) {
			jgen.writeStartObject();
			jgen.writeStringField(MESSAGE, exception.get().getMessage());
			jgen.writeEndObject();
		} else {
			provider.defaultSerializeNull(jgen);
		}
	}

}
