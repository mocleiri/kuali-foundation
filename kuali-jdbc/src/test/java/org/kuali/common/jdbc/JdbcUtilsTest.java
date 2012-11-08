package org.kuali.common.jdbc;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
			String dbaUser = properties.getProperty("jdbc.dba.username");
			String dbaUrl = properties.getProperty("jdbc.dba.url");
			String db = properties.getProperty("sql.database");
			logger.info("Validating database credentials for [{}] on [{}]", dbaUser, dbaUrl);
			logger.info("Executed " + dba.executeString(properties.getProperty("sql.validate")) + " SQL statements");
			logger.info("Dropping database [{}] on [{}]", db, dbaUrl);
			logger.info("Executed " + dba.executeString(properties.getProperty("sql.dba.drop")) + " SQL statements");
			logger.info("Creating database [{}] on [{}]", db, dbaUrl);
			logger.info("Executed " + dba.executeString(properties.getProperty("sql.dba.create")) + " SQL statements");
			String user = properties.getProperty("jdbc.username");
			String url = properties.getProperty("jdbc.url");
			logger.info("Validating database credentials for [{}] on [{}]", user, url);
			logger.info("Executed " + jdbcUtils.executeString(properties.getProperty("sql.validate")) + " SQL statements");
			String schema = properties.getProperty("sql.source.schema");
			String schemaLocation = properties.getProperty("sql.schema");
			String schemaConstraintsLocation = properties.getProperty("sql.schema.constraints");
			logger.info("Creating schema [{}] on database [{}] at [" + url + "]", schema, db);
			logger.info("Executed " + jdbcUtils.executeSQL(schemaLocation) + " SQL statements");
			logger.info("Adding schema constraints for [{}] on database [{}] at [" + url + "]", schema, db);
			logger.info("Executed " + jdbcUtils.executeSQL(schemaConstraintsLocation) + " SQL statements");
			logger.info("Elapsed: " + (System.currentTimeMillis() - start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
