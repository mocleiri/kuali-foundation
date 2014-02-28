package org.kuali.common.util.system;

import static java.io.File.pathSeparatorChar;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Splitter;

public class PathDeserializer extends JsonDeserializer<List<String>> {

	private final Splitter splitter = Splitter.on(pathSeparatorChar);

	@Override
	public List<String> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return splitter.splitToList(jp.getText());
	}

}
