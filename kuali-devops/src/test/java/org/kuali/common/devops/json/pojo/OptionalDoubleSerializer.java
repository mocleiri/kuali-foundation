package org.kuali.common.devops.json.pojo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.common.base.Optional;

public class OptionalDoubleSerializer extends StdSerializer<Optional<Double>> {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public OptionalDoubleSerializer() {
		super(MAPPER.getTypeFactory().constructParametricType(Optional.class, Double.class));
	}

	@Override
	public void serialize(Optional<Double> optional, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
		jgen.writeStartObject();
		if (optional.isPresent()) {
			jgen.writeNumber(optional.get());
		} else {
			jgen.writeNull();
		}
		jgen.writeEndObject();
	}

}
