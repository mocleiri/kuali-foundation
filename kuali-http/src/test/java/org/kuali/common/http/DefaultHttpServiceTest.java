package org.kuali.common.http;

import org.junit.Test;

public class DefaultHttpServiceTest {

	@Test
	public void test() {
		HttpContext context = new HttpContext();
		context.setUrl("http://env16.ks.kuali.org");
		HttpService service = new DefaultHttpService();
		service.wait(context);
	}

}
