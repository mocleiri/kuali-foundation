/**
 * Copyright 2014-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.core.json.jackson;

import static com.google.common.collect.Lists.newArrayList;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Splitter;

/**
 * Used to parse delimited strings into {@code List<File>}. For example, the system property {@code java.class.path} for a JVM running Tomcat is typically something like this:
 * 
 * <pre>
 * {@code /usr/local/tomcat/bin/bootstrap.jar:/usr/local/tomcat/bin/tomcat-juli.jar}
 * </pre>
 * 
 * This deserializer splits the string (using {@code File.pathSeparatorChar}) into a list of files.
 */
public class PathDeserializer extends JsonDeserializer<List<File>> {

	private final Splitter splitter = Splitter.on(File.pathSeparatorChar);

	@Override
	public List<File> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		List<String> tokens = splitter.splitToList(jp.getText());
		List<File> files = newArrayList();
		for (String token : tokens) {
			String canonical = new File(token).getCanonicalPath();
			files.add(new File(canonical));
		}
		return files;
	}

}
