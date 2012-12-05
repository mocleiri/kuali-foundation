package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeProcessorTest {
	private static final Logger logger = LoggerFactory.getLogger(HomeProcessorTest.class);

	protected HomeProcessor getHomeProcessor() {
		return new HomeProcessor(OrgProcessorTest.KUALI_GROUPID_KEY, OrgProcessorTest.KUALI_PROJECT_GROUPID_KEY, GlobalPropertiesMode.BOTH);
	}

	@Test
	public void testProcess() {
		OrgProcessor op = OrgProcessorTest.getProcessor();
		HomeProcessor hp = getHomeProcessor();
		Properties properties = OrgProcessorTest.getProperties();
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
