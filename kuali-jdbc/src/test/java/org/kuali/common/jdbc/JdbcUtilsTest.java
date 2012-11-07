package org.kuali.common.jdbc;

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
	private Reset reset = null;

	@Test
	public void test() {
		try {
			long start = System.currentTimeMillis();
			logger.info("Jdbc Utils Test");
			Assert.assertNotNull("jdbcUtils is null.", jdbcUtils);
			Assert.assertNotNull("dba is null.", dba);
			logger.info("Validating DBA credentials");
			logger.info("Executed " + dba.execute(reset.getValidate()) + " SQL statements");
			logger.info("Drop database");
			logger.info("Executed " + dba.execute(reset.getDrop()) + " SQL statements");
			logger.info("Create database");
			logger.info("Executed " + dba.execute(reset.getCreate()) + " SQL statements");
			logger.info("Validating database credentials");
			logger.info("Executed " + jdbcUtils.execute(reset.getValidate()) + " SQL statements");
			logger.info("Creating schema");
			logger.info("Executed " + jdbcUtils.execute("classpath:sql/mysql/rice-impex-master.sql") + " SQL statements");
			logger.info("Executed " + jdbcUtils.execute("classpath:sql/mysql/rice-impex-master-constraints.sql") + " SQL statements");
			logger.info("Elapsed: " + (System.currentTimeMillis() - start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
