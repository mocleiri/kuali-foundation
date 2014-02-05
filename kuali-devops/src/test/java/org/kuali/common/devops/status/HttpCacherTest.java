package org.kuali.common.devops.status;

import org.junit.Test;
import org.kuali.common.devops.logic.HttpCacher;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

public class HttpCacherTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			String url = "http://env1.ks.kuali.org/tomcat/logs/env.jsp";
			HttpCacher.cache(url);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
