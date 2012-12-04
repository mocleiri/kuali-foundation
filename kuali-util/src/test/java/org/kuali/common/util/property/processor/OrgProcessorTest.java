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

	protected static OrgProcessor getProcessor() {
		OrgProcessor processor = new OrgProcessor();
		processor.setGroupId("org.kuali");
		processor.setProjectGroupId("org.kuali.ole");
		processor.setGroupIdKey("kuali.groupId");
		processor.setGroupCodeKey("kuali.groupCode");
		processor.setProjectGroupIdKey("kuali.project.groupId");
		processor.setProjectGroupCodeKey("kuali.project.groupCode");
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
