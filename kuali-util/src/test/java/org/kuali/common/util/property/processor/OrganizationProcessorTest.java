package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrganizationProcessorTest {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationProcessorTest.class);

	OrganizationProcessor processor = getProcessor();

	protected static OrganizationProcessor getProcessor() {
		OrganizationProcessor processor = new OrganizationProcessor();
		processor.setOrganizationId("org.kuali");
		processor.setGroupId("org.kuali.ole");
		processor.setOrganizationIdKey("kuali.orgId");
		processor.setOrganizationCodeKey("kuali.orgCode");
		processor.setGroupIdKey("kuali.groupId");
		processor.setGroupCodeKey("kuali.groupCode");
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
