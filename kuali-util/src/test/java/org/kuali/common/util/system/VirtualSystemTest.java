package org.kuali.common.util.system;

import static org.junit.Assert.assertEquals;
import static org.kuali.common.util.system.VirtualSystemFactory.newVirtualSystem;

import org.junit.Test;
import org.kuali.common.util.json.api.JsonService;
import org.kuali.common.util.json.jackson.JacksonJsonService;

public class VirtualSystemTest {

	@Test
	public void test() {
		VirtualSystem vs = newVirtualSystem();
		JsonService service = new JacksonJsonService();
		String expected = service.writeString(vs);
		String actual = service.writeString(service.readString(expected, VirtualSystem.class));
		assertEquals(expected, actual);
	}

}
