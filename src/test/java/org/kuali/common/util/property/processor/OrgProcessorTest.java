package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrgProcessorTest {
	private static final Logger logger = LoggerFactory.getLogger(OrgProcessorTest.class);

	OrgProcessor processor = getProcessor();

	protected static final String KUALI_GROUPID_KEY = "kuali.groupId";
	protected static final String KUALI_PROJECT_GROUPID_KEY = "project.groupId";

	protected static OrgProcessor getProcessor() {
		return new OrgProcessor(KUALI_GROUPID_KEY, KUALI_PROJECT_GROUPID_KEY);
	}

	protected static Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty(KUALI_GROUPID_KEY, "org.kuali");
		properties.setProperty(KUALI_PROJECT_GROUPID_KEY, "org.kuali.ole");
		return properties;
	}

	@Test
	public void testProcess() {
		Properties properties = getProperties();
		processor.process(properties);
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String value = properties.getProperty(key);
			logger.info(key + "=" + value);
		}
	}

}
