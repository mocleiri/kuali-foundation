package org.kuali.common.util.spring;

import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SimplePropertyFactoryBeanTest {

	private static final Logger logger = LoggerFactory.getLogger(SimplePropertyFactoryBeanTest.class);

	@Autowired
	private Properties properties = null;

	@Test
	public void test() {
		try {
			Assert.assertNotNull("properties can't be null.", properties);
			List<String> keys = PropertyUtils.getSortedKeys(properties);
			for (String key : keys) {
				String value = properties.getProperty(key);
				logger.info(key + "=" + Str.flatten(value, "CR", "LF"));
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
