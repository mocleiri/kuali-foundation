package org.kuali.common.core.april.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DaySerializer extends JsonSerializer<Long> {

	@Override
	public void serialize(Long value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
		SimpleDateFormat formatter = new SimpleDateFormat(DayDeserializer.DAY_FORMAT);
		Date date = new Date(value);
		jgen.writeString(formatter.format(date));
	}
}
