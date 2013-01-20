package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultJdbcServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultJdbcServiceTest.class);
	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	Properties properties = getProperties();
	SqlReader reader = new DefaultSqlReader();

	protected Properties getProperties() {
		Properties sql = PropertyUtils.load("classpath:org/kuali/common/sql/mysql.xml");
		Properties jdbc1 = PropertyUtils.load("classpath:org/kuali/common/jdbc/jdbc.properties");
		Properties jdbc2 = PropertyUtils.load("classpath:org/kuali/common/deploy/jdbc.properties");
		Properties properties = PropertyUtils.combine(sql, jdbc1, jdbc2);
		properties.setProperty("db.vendor", "mysql");
		properties.setProperty("jdbc.username", "JDBCTEST");
		return properties;
	}

	protected String getValue(String key) {
		String value = properties.getProperty(key);
		return helper.replacePlaceholders(value, properties);
	}

	protected JdbcContext getJdbcDba() {
		String url = getValue("jdbc.dba.url");
		String driver = getValue("jdbc.driver");
		JdbcContext context = new JdbcContext();
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, "root", null);
		dataSource.setDriverClassName(driver);
		context.setDataSource(dataSource);
		return context;
	}

	protected JdbcContext getJdbc() {
		String url = getValue("jdbc.url");
		String driver = getValue("jdbc.driver");
		JdbcContext context = new JdbcContext();
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, getValue("jdbc.username"), getValue("jdbc.password"));
		dataSource.setDriverClassName(driver);
		context.setDataSource(dataSource);
		return context;
	}

	protected ExecutionContext getDba() {
		ExecutionContext ec = new ExecutionContext();
		ec.setJdbcContext(getJdbcDba());
		ec.setReader(reader);
		ec.setSql(Arrays.asList(getValue("sql.drop"), getValue("sql.create")));
		return ec;
	}

	protected ExecutionContext getNormal() {
		ExecutionContext ec = new ExecutionContext();
		ec.setJdbcContext(getJdbc());
		ec.setReader(reader);
		ec.setLocations(getLocations("mysql", "rice-impex-server-bootstrap"));
		ec.setThreads(3);
		return ec;
	}

	@Test
	public void testReset() {
		try {
			JdbcService service = new DefaultJdbcService();
			service.executeSql(getDba());
			service.executeSql(getNormal());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void test() {
		try {
			SqlReader reader = new DefaultSqlReader();
			JdbcService service = new DefaultJdbcService();
			String encoding = "UTF-8";

			List<String> locations = getLocations("oracle", "rice-impex-master");
			List<SqlMetaData> smdl = service.getMetaData(reader, locations, encoding);

			long count = 0;
			long size = 0;
			for (int i = 0; i < locations.size(); i++) {
				SqlMetaData smd = smdl.get(i);
				String location = locations.get(i);
				count += smd.getCount();
				size += smd.getSize();
				String ct = StringUtils.leftPad(FormatUtils.getCount(smd.getCount()), 5, " ");
				String sz = StringUtils.rightPad(FormatUtils.getSize(smd.getSize()), 10, " ");
				logger.info(ct + "  " + sz + " " + location);
			}

			String c = FormatUtils.getCount(count);
			String s = FormatUtils.getSize(size);

			logger.info("Count: {} Size: {}", c, s);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getSchemaLocation(String vendor, String schema) {
		return "classpath:sql/" + vendor + "/" + schema + ".sql";
	}

	protected String getConstraintsLocation(String vendor, String schema) {
		return "classpath:sql/" + vendor + "/" + schema + "-constraints.sql";
	}

	protected List<String> getLocations(String vendor, String schema) {
		List<String> locations = new ArrayList<String>();
		locations.add(getSchemaLocation(vendor, schema));
		locations.addAll(LocationUtils.getLocations("classpath:META-INF/mysql/rice-impex-server-bootstrap.tables"));
		locations.add("classpath:sql/" + vendor + "/" + schema + "-constraints.sql");
		return locations;
	}

	protected List<String> getLocations(List<String> tables) {
		List<String> locations = new ArrayList<String>();
		for (String table : tables) {
			String location = "classpath:sql/oracle/" + table + ".sql";
			locations.add(location);
		}
		return locations;
	}

}
