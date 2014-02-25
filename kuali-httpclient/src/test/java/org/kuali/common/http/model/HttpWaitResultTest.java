package org.kuali.common.http.model;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;
import static org.kuali.common.http.model.HttpRequestResult.newHttpRequestResult;
import static org.kuali.common.http.model.HttpWaitResult.newHttpWaitResult;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

public class HttpWaitResultTest {

	@Test
	public void test() throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new GuavaModule());
			HttpRequestResult hrr = newHttpRequestResult(new IOException("uhoh"), currentTimeMillis());
			HttpWaitResult result1 = newHttpWaitResult(HttpStatus.SUCCESS, hrr, currentTimeMillis());
			// String json1 = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(result1);
			String json1 = mapper.writeValueAsString(result1);
			System.out.println(json1);
			HttpWaitResult result2 = mapper.readValue(json1, HttpWaitResult.class);
			String json2 = mapper.writeValueAsString(result2);
			System.out.println(json2);
			assertEquals(json1, json2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
