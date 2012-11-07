package org.kuali.common.util.property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

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
public class PropertyFactoryBeanTest {

	private static final Logger logger = LoggerFactory.getLogger(PropertyFactoryBeanTest.class);

	@Autowired
	private Properties properties = null;

	@Test
	public void test() {
		try {
			Assert.assertNotNull("properties is null.", properties);
			List<String> keys = new ArrayList<String>(properties.stringPropertyNames());
			Collections.sort(keys);
			for (String key : keys) {
				String value = properties.getProperty(key);
				logger.info(key + "=" + value);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
