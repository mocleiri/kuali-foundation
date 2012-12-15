package org.kuali.common.util.service;

import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.LocationUtils;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

public class SpringContextLoadingTest {

	@Test
	public void test() {

		Properties properties = new Properties();
		properties.setProperty("foo", "bar");
		String contextXML = LocationUtils.toString("classpath:");
		Resource resource = new ByteArrayResource(contextXML.getBytes());
		GenericXmlApplicationContext springContext = new GenericXmlApplicationContext();
	}
}
