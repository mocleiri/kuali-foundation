package org.kuali.common.jdbc;

import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JdbcUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(JdbcUtilsTest.class);

	@Autowired
	private JdbcUtils jdbcUtils = null;

	@Autowired
	private JdbcUtils dba = null;

	@Autowired
	private Properties properties = null;

	@Test
	public void test() {
		try {
			long start = System.currentTimeMillis();
			logger.info("Jdbc Utils Test");
			Assert.assertNotNull("jdbcUtils is null.", jdbcUtils);
			Assert.assertNotNull("dba is null.", dba);
			List<String> keys = PropertyUtils.getSortedKeys(properties);
			for (String key : keys) {
				String value = properties.getProperty(key);
				logger.info(key + "=" + Str.flatten(value));
			}
			logger.info("Validating DBA credentials");
			logger.info("Executed " + dba.executeString(properties.getProperty("impex.validate")) + " SQL statements");
			logger.info("Drop database");
			logger.info("Executed " + dba.executeString(properties.getProperty("impex.dba.drop")) + " SQL statements");
			logger.info("Create database");
			logger.info("Executed " + dba.executeString(properties.getProperty("impex.dba.create")) + " SQL statements");
			logger.info("Validating database credentials");
			logger.info("Executed " + jdbcUtils.executeString(properties.getProperty("impex.validate")) + " SQL statements");
			logger.info("Creating schema");
			// logger.info("Executed " + jdbcUtils.executeSQL("classpath:sql/mysql/rice-impex-master.sql") + " SQL statements");
			// logger.info("Executed " + jdbcUtils.executeSQL("classpath:sql/mysql/rice-impex-master-constraints.sql") + " SQL statements");
			logger.info("Elapsed: " + (System.currentTimeMillis() - start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
