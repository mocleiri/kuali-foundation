package org.kuali.common.http;

import org.junit.Ignore;
import org.junit.Test;

public class DefaultHttpServiceTest {

	@Test
	@Ignore
	public void test() {
		HttpContext context = new HttpContext();
		context.setUrl("http://env16.ks.kuali.org");
		HttpService service = new DefaultHttpService();
		service.wait(context);
	}

}
