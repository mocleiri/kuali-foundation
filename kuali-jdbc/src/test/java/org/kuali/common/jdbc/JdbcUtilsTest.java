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

	@Test
	public void test() {
		logger.info("Hello World");
		Assert.assertNotNull("jdbcUtils is null.", jdbcUtils);
		Assert.assertNotNull("dataSource is null.", jdbcUtils.getDataSource());
		boolean executed = jdbcUtils.execute("SELECT 1");
		Assert.assertTrue(executed);
	}

}
