package org.kuali.common.core.json.jackson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CanonicalFileSerializer extends StdSerializer<File> {

	public CanonicalFileSerializer(Class<File> type) {
		super(type);
	}

	@Override
	public void serialize(File file, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
		jgen.writeString(file.getCanonicalPath());
	}

}
