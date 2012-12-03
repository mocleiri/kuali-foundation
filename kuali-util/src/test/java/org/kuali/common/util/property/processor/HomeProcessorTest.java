package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeProcessorTest {
	private static final Logger logger = LoggerFactory.getLogger(HomeProcessorTest.class);

	HomeProcessor processor = getProcessor();

	protected HomeProcessor getProcessor() {
		HomeProcessor processor = new HomeProcessor();
		return processor;
	}

	@Test
	public void testProcess() {
		Properties properties = new Properties();
		processor.process(properties);
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String value = properties.getProperty(key);
			logger.info(key + "=" + value);
		}
	}

}
