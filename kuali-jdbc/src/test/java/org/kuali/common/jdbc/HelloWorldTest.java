package org.kuali.common.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class HelloWorldTest {
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldTest.class);

	@Autowired
	private HelloWorld helloWorld = null;

	@Test
	public void test() {
		logger.info("Hello World");
		Assert.assertNotNull("helloWorld is null.", helloWorld);
		String msg = helloWorld.getMessage();
		Assert.assertNotNull("Message is null.", msg);
		String expectedMessage = "Hello World";
		Assert.assertEquals("Message should be '" + expectedMessage + "'.", expectedMessage, msg);
		logger.info("Success!!!! message='{}'", msg);
	}

}
