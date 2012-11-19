package org.kuali.common.util.spring;

import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.PropertyStoreContext;
import org.kuali.common.util.service.PropertyService;
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

	@Autowired
	private PropertyService service = null;

	@Autowired
	private PropertyStoreContext encryptedStorage = null;

	@Autowired
	private PropertyStoreContext normalStorage = null;

	@Test
	public void test() {
		try {
			Assert.assertNotNull("properties can't be null.", properties);
			List<String> keys = PropertyUtils.getSortedKeys(properties);
			for (String key : keys) {
				String value = properties.getProperty(key);
				logger.info(key + "=" + value);
			}
			service.store(encryptedStorage, properties);
			service.store(normalStorage, properties);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
