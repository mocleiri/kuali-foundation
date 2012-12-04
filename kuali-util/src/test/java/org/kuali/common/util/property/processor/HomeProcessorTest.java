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
		HomeProcessor p = new HomeProcessor();
		p.setOrganizationCodeKey("kuali.organization.id");
		p.setOrganizationHomeKey("kuali.home");
		p.setGroupCodeKey("kuali.group.code");
		p.setGroupHomeKey("kuali.${kuali.group.code}.home");
		return p;
	}

	@Test
	public void testProcess() {
		OrganizationProcessor op = OrganizationProcessorTest.getProcessor();
		HomeProcessor hp = getHomeProcessor();
		Properties properties = new Properties();
		op.process(properties);
		showProperties(properties);
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
