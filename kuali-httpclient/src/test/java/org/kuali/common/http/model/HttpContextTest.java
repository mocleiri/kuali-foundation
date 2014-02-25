package org.kuali.common.http.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

public class HttpContextTest {

	@Test
	public void test() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new GuavaModule());
		HttpContext context1 = HttpContext.builder("http://www.yahoo.com").build();
		String json1 = mapper.writeValueAsString(context1);
		System.out.println(json1);
		HttpContext context2 = mapper.readValue(json1, HttpContext.class);
		String json2 = mapper.writeValueAsString(context2);
		System.out.println(json2);
		assertEquals(json1, json2);
	}

}
