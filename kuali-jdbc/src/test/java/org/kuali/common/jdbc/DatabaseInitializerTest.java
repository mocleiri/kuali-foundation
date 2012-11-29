package org.kuali.common.jdbc;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.jdbc.context.DatabaseInitializeContext;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DatabaseInitializerTest {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializerTest.class);

	@Autowired
	DatabaseInitializeContext context = null;

	@Autowired
	DatabaseService service = null;

	@Test
	public void initializeDatabaseTest() {
		Properties properties = context.getProperties();
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			// logger.info(key + "=" + Str.flatten(properties.getProperty(key)));
		}
		service.initialize(context);
	}
}
