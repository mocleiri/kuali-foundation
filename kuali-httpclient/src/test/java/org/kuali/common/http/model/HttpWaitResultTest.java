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
