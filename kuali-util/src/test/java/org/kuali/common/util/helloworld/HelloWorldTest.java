package org.kuali.common.util.helloworld;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import javax.validation.Validation;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.collect.Lists;

public class HelloWorldTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			ServiceLoader<HelloWorldService> loader = ServiceLoader.load(HelloWorldService.class);
			Iterator<HelloWorldService> itr = loader.iterator();
			List<HelloWorldService> services = Lists.newArrayList();
			while (itr.hasNext()) {
				services.add(itr.next());
			}
			for (HelloWorldService service : services) {
				logger.info(String.format("service=%s", service.getClass().getCanonicalName()));
			}
			for (HelloWorldService service : services) {
				service.sayHello();
			}
			Validation.buildDefaultValidatorFactory();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
