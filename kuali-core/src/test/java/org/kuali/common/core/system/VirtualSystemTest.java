package org.kuali.common.core.system;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.junit.Test;
import org.kuali.common.core.json.api.JsonService;
import org.kuali.common.core.json.jackson.JacksonJsonService;
import org.slf4j.Logger;

public class VirtualSystemTest {

	private static final Logger logger = newLogger();

	@Test
	public void test() {
		// This implicitly tests jackson + hibernate validator
		VirtualSystem vs = VirtualSystem.create();

		// Get a reference to the default json service (no custom mixin's)
		JsonService service = new JacksonJsonService();

		// object -> string
		String expected = service.writeString(vs);

		// string -> object -> string
		String actual = service.writeString(service.readString(expected, VirtualSystem.class));

		// If everything is in working order, these 2 strings will be equal
		assertEquals(expected, actual);

		logger.info(format("user.name=%s", vs.getUser().getName()));
	}

	@Test
	public void testValidationFail() {
		try {
			VirtualSystem.builder().build();
			fail("This should have failed");
		} catch (IllegalArgumentException e) {
		}
	}
}
