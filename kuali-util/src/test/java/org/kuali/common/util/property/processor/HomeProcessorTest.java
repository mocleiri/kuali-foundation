package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeProcessorTest {
	private static final Logger logger = LoggerFactory.getLogger(HomeProcessorTest.class);

	protected HomeProcessor getHomeProcessor() {
		return new HomeProcessor("kuali.groupId", "kuali.project.groupId");
	}

	@Test
	public void testProcess() {
		OrgProcessor op = OrgProcessorTest.getProcessor();
		HomeProcessor hp = getHomeProcessor();
		Properties properties = new Properties();
		properties.setProperty("kuali.groupId", "org.kuali");
		properties.setProperty("kuali.project.groupId", "org.kuali.ole");
		properties.setProperty("user.home", System.getProperty("user.home"));
		op.process(properties);
		hp.process(properties);
		showProperties(properties);
	}

	protected void showProperties(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String value = properties.getProperty(key);
			logger.info(key + "=" + value);
		}
	}

}
