package org.kuali.common.core.json.jackson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CanonicalFileDeserializer extends JsonDeserializer<File> {

	@Override
	public File deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return new File(jp.getText()).getCanonicalFile();
	}

}
