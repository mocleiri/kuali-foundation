package org.kuali.common.util.system;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.kuali.common.util.json.api.JsonService;
import org.kuali.common.util.json.jackson.JacksonJsonService;

public class VirtualSystemTest {

	@Test
	public void test() {
		// This implicitly tests jackson + hibernate validator
		VirtualSystem vs = VirtualSystem.create();

		// Get a reference to the default json service (no custom mixin's)
		JsonService service = new JacksonJsonService();

		// Test object -> string
		String expected = service.writeString(vs);

		// Test string -> object -> string
		String actual = service.writeString(service.readString(expected, VirtualSystem.class));

		// Make sure both strings are equal and that no information was lost
		assertEquals(expected, actual);
	}
}
