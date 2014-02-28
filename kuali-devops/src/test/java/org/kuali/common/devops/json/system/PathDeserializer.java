package org.kuali.common.devops.json.system;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.common.base.Splitter;

public class PathDeserializer extends StdDeserializer<List<String>> {

	private static final long serialVersionUID = 1L;
	private final Splitter splitter = Splitter.on(File.pathSeparatorChar);

	public PathDeserializer(Class<List<String>> type) {
		super(type);
	}

	@Override
	public List<String> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return splitter.splitToList(jp.getText());
	}

}
