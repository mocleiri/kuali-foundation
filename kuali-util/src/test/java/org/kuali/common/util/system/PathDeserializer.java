package org.kuali.common.util.system;

import static com.google.common.collect.Lists.newArrayList;
import static java.io.File.pathSeparatorChar;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.kuali.common.util.file.CanonicalFile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Splitter;

public class PathDeserializer extends JsonDeserializer<List<File>> {

	private final Splitter splitter = Splitter.on(pathSeparatorChar);

	@Override
	public List<File> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		List<String> tokens = splitter.splitToList(jp.getText());
		List<File> files = newArrayList();
		for (String token : tokens) {
			String canonical = new CanonicalFile(token).getPath();
			files.add(new File(canonical));
		}
		return files;
	}

}
