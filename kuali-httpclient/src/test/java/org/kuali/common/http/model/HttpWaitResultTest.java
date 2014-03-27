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
package org.kuali.common.http.model;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;
import static org.kuali.common.http.model.HttpRequestResult.newHttpRequestResult;
import static org.kuali.common.http.model.HttpStatus.IO_EXCEPTION;
import static org.kuali.common.http.model.HttpStatus.SUCCESS;
import static org.kuali.common.http.model.HttpWaitResult.newHttpWaitResult;

import java.io.IOException;
import java.net.ConnectException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.base.Optional;

public class HttpWaitResultTest {

	private final boolean pretty = true;

	@Test
	public void testWithException() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new GuavaModule());
		HttpRequestResult hrr = newHttpRequestResult(new ConnectException("uhoh"), currentTimeMillis());
		HttpWaitResult result1 = newHttpWaitResult(IO_EXCEPTION, hrr, currentTimeMillis());
		String json1 = writeString(mapper, result1);
		System.out.println(json1);
		HttpWaitResult result2 = mapper.readValue(json1, HttpWaitResult.class);
		String json2 = writeString(mapper, result2);
		System.out.println(json2);
		assertEquals(json1, json2);
	}

	@Test
	public void testWithoutException() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new GuavaModule());
		HttpRequestResult hrr = newHttpRequestResult("OK", 200, Optional.of("Hello World"), currentTimeMillis());
		HttpWaitResult result1 = newHttpWaitResult(SUCCESS, hrr, currentTimeMillis());
		String json1 = writeString(mapper, result1);
		System.out.println(json1);
		HttpWaitResult result2 = mapper.readValue(json1, HttpWaitResult.class);
		String json2 = writeString(mapper, result2);
		System.out.println(json2);
		assertEquals(json1, json2);
	}

	protected <T> String writeString(ObjectMapper mapper, T reference) throws IOException {
		if (pretty) {
			return mapper.writer().withDefaultPrettyPrinter().writeValueAsString(reference);
		} else {
			return mapper.writeValueAsString(reference);
		}
	}
}
