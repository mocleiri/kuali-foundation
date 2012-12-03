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

	protected OrganizationProcessor getProcessor() {
		OrganizationProcessor processor = new OrganizationProcessor();
		processor.setOrganizationId("org.kuali");
		processor.setGroupId("org.kuali.ole");
		processor.setOrganizationIdKey("kuali.organization.id");
		processor.setOrganizationCodeKey("kuali.organization.code");
		processor.setGroupIdKey("kuali.group.id");
		processor.setGroupCodeKey("kuali.group.code");
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
