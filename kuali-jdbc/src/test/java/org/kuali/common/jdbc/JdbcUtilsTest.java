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
	private JdbcUtils dbaJdbcUtils = null;

	@Test
	public void test() {
		try {
			logger.info("Hello World");
			Assert.assertNotNull("dbaJdbcUtils is null.", dbaJdbcUtils);
			Assert.assertNotNull("dataSource is null.", dbaJdbcUtils.getDataSource());
			Properties props = ResourceUtils.getProperties("classpath:mysql.xml");
			String sql = props.getProperty("sql.mysql.validate");
			dbaJdbcUtils.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
