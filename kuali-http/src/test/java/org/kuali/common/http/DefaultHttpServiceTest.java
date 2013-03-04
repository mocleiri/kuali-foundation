package org.kuali.common.http;

import org.junit.Test;

public class DefaultHttpServiceTest {

	@Test
	public void test() {
		HttpContext context = new HttpContext();
		context.setUrl("http://www.google.com");
		HttpService service = new DefaultHttpService();
		service.wait(context);
	}

}
