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
