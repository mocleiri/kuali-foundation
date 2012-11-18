package org.kuali.common.util.spring;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.DefaultPropertyStoreContext;
import org.kuali.common.util.property.PropertyEncryptionMode;
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
	private PropertyStoreContext context = null;

	@Test
	public void test() {
		try {
			Assert.assertNotNull("properties can't be null.", properties);
			List<String> keys = PropertyUtils.getSortedKeys(properties);
			for (String key : keys) {
				String value = properties.getProperty(key);
				logger.info(key + "=" + value);
			}
			String password = properties.getProperty("enc.password");
			String buildDir = properties.getProperty("project.build.directory");
			service.store(context, properties);
			DefaultPropertyStoreContext dpsc = new DefaultPropertyStoreContext();
			String filename = buildDir + "/properties/decrypted.properties";
			dpsc.setFile(new File(filename));
			dpsc.setEncryptionMode(PropertyEncryptionMode.DECRYPT);
			dpsc.setEncryptionPassword(password);
			service.store(dpsc, properties);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
