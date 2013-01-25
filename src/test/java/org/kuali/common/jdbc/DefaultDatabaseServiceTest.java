package org.kuali.common.jdbc;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultDatabaseServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDatabaseServiceTest.class);

	protected Properties loadProperties() {
		String vendor = System.getProperty("db.vendor") == null ? "mysql" : System.getProperty("db.vendor");
		boolean mysqlRice = Boolean.getBoolean("mysql.rice");
		Properties sql1 = PropertyUtils.load("classpath:org/kuali/common/sql/mysql.xml");
		Properties sql2 = PropertyUtils.load("classpath:org/kuali/common/sql/oracle.xml");
		Properties jdbc1 = PropertyUtils.load("classpath:org/kuali/common/jdbc/jdbc.properties");
		Properties jdbc2 = PropertyUtils.load("classpath:org/kuali/common/deploy/jdbc.properties");
		Properties service = PropertyUtils.load("classpath:org/kuali/common/jdbc/service.properties");
		Properties ole = PropertyUtils.load("classpath:ole-fs.properties");
		Properties properties = PropertyUtils.combine(sql1, sql2, jdbc1, jdbc2, ole, service);
		properties.setProperty("db.vendor", vendor);
		properties.setProperty("jdbc.username", "JDBCTEST");
		properties.setProperty("oracle.dba.url", "jdbc:oracle:thin:@oracle.ks.kuali.org:1521:ORACLE");
		properties.setProperty("oracle.dba.username", "master");
		properties.setProperty("oracle.dba.password", "gw570229");
		if (mysqlRice) {
			mysqlRice(properties);
		} else {
			mysqlLocalhost(properties);
		}
		return properties;
	}

	protected void mysqlLocalhost(Properties properties) {
		properties.setProperty("mysql.dba.url", "jdbc:mysql://localhost");
		properties.setProperty("mysql.dba.username", "root");
		properties.setProperty("mysql.dba.password", "NONE");
	}

	protected void mysqlRice(Properties properties) {
		properties.setProperty("mysql.dba.url", "jdbc:mysql://mysql.rice.kuali.org");
		properties.setProperty("mysql.dba.username", "master");
		properties.setProperty("mysql.dba.password", "gw570229");
	}

	protected Properties getResolvedProperties(Properties properties) {
		PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
		Properties newProperties = new Properties();
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String originalValue = properties.getProperty(key);
			String resolvedValue = helper.replacePlaceholders(originalValue, properties);
			newProperties.setProperty(key, resolvedValue);
		}
		return newProperties;
	}

	@Test
	public void execute() {
		try {
			Properties original = loadProperties();
			Properties properties = getResolvedProperties(original);
			PropertyUtils.info(properties);
			logger.info("db.vendor=" + properties.getProperty("db.vendor"));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
