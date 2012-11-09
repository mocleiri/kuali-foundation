package org.kuali.common.util.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ToStringFactoryBeanTest {

	private static final Logger logger = LoggerFactory.getLogger(ToStringFactoryBeanTest.class);

	@Autowired
	private String password;

	@Test
	public void test() {
		try {
			Assert.assertNotNull("password is null.", password);
			logger.info(Str.flatten(password, "CR", "LF"));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
