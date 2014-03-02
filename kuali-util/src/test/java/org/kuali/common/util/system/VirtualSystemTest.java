package org.kuali.common.util.system;

import static com.google.common.base.Stopwatch.createStarted;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.json.api.JsonService;
import org.kuali.common.util.json.jackson.JacksonJsonService;

import com.google.common.base.Stopwatch;

public class VirtualSystemTest {

	@Test
	public void test() {
		Stopwatch sw = createStarted();
		VirtualSystem vs = VirtualSystem.create();
		JsonService service = new JacksonJsonService();
		String expected = service.writeString(vs);
		String actual = service.writeString(service.readString(expected, VirtualSystem.class));
		assertEquals(expected, actual);
		System.out.println(FormatUtils.getTime(sw));
	}

}
