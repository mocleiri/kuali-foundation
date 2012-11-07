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
			logger.info("Jdbc Utils Test");
			Assert.assertNotNull("jdbcUtils is null.", jdbcUtils);
			Assert.assertNotNull("dba is null.", dba);
			dba.execute(reset.getValidate());
			dba.execute(reset.getDrop());
			dba.execute(reset.getCreate());
			jdbcUtils.execute(reset.getValidate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
