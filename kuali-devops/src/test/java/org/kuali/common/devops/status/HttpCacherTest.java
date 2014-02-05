package org.kuali.common.devops.status;

import java.io.File;

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
			File file = HttpCacher.cache(url);
			logger.info(String.format("file -> %s", file));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
