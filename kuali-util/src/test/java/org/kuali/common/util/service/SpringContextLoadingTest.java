package org.kuali.common.util.service;

import java.util.Properties;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextLoadingTest {

	@Test
	public void test() {

		Properties properties = new Properties();
		properties.setProperty("spring.message", "Good bye!");

		ClassPathXmlApplicationContext parent = new ClassPathXmlApplicationContext();
		parent.refresh();
		parent.getBeanFactory().registerSingleton("properties", properties);

		String[] locations = new String[] { "classpath:org/kuali/common/util/child-context.xml" };

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(locations, false, parent);
		context.refresh();
	}
}
