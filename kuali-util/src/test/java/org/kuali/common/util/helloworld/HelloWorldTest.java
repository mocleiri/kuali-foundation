package org.kuali.common.util.helloworld;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

public class HelloWorldTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			ServiceLoader<HelloWorldService> loader = ServiceLoader.load(HelloWorldService.class);
			Iterator<HelloWorldService> itr = loader.iterator();
			while (itr.hasNext()) {
				HelloWorldService service = itr.next();
				logger.info(String.format("service=%s", service.getClass().getCanonicalName()));
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
