package org.kuali.common.jdbc;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.jdbc.context.DatabaseResetContext;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DatabaseServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseServiceTest.class);

	@Autowired
	DatabaseResetContext context = null;

	@Autowired
	DatabaseService service = null;

	@Autowired
	Properties properties = null;

	@Test
	public void resetDatabaseTest() {
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String value = properties.getProperty(key);
			logger.info(key + "=" + Str.flatten(value));
		}
		// service.reset(context);
	}
}
