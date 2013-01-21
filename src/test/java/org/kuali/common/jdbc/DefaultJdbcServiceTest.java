package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.LogSqlListener;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.ProgressListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.listener.SummaryListener;
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
	SqlReader reader = new DefaultSqlReader();
	String vendor = "mysql";
	List<String> schemas = Arrays.asList("rice-impex-server-bootstrap");
	Properties properties = getProperties();

	protected Properties getProperties() {
		Properties sql = PropertyUtils.load("classpath:org/kuali/common/sql/mysql.xml");
		Properties jdbc1 = PropertyUtils.load("classpath:org/kuali/common/jdbc/jdbc.properties");
		Properties jdbc2 = PropertyUtils.load("classpath:org/kuali/common/deploy/jdbc.properties");
		Properties properties = PropertyUtils.combine(sql, jdbc1, jdbc2);
		properties.setProperty("db.vendor", vendor);
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

	protected ExecutionContext getDbaContext() {
		ExecutionContext ec = new ExecutionContext();
		ec.setJdbcContext(getJdbcDba());
		ec.setReader(reader);
		ec.setSql(Arrays.asList(getValue("sql.drop"), getValue("sql.create")));
		ec.setListener(getDbaListener());
		return ec;
	}

	protected SqlListener getDbaListener() {
		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new LogSqlListener());
		listeners.add(new SummaryListener());
		return new NotifyingListener(listeners);
	}

	protected SqlListener getSchemaListener() {
		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new SummaryListener());
		listeners.add(new ProgressListener());
		return new NotifyingListener(listeners);
	}

	protected ExecutionContext getSchemasContext() {
		ExecutionContext ec = new ExecutionContext();
		ec.setJdbcContext(getJdbc());
		ec.setReader(reader);
		ec.setLocations(getSchemaLocations(vendor, schemas));
		ec.setListener(getSchemaListener());
		return ec;
	}

	protected ExecutionContext getDataContext() {
		ExecutionContext ec = new ExecutionContext();
		ec.setJdbcContext(getJdbc());
		ec.setReader(reader);
		ec.setLocations(getDataLocations(vendor, schemas));
		ec.setThreads(10);
		return ec;
	}

	@Test
	public void testReset() {
		try {
			JdbcService service = new DefaultJdbcService();
			service.executeSql(getDbaContext());
			service.executeSql(getSchemasContext());
			service.executeSql(getDataContext());
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

			List<String> locations = new ArrayList<String>();// getLocations("oracle", "rice-impex-master");
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

	protected List<String> getSchemaLocations(String vendor, List<String> schemas) {
		List<String> locations = new ArrayList<String>();
		for (String schema : schemas) {
			locations.add(getSchemaLocation(vendor, schema));
		}
		return locations;
	}

	protected String getSchemaLocation(String vendor, String schema) {
		return "classpath:sql/" + vendor + "/" + schema + ".sql";
	}

	protected String getConstraintsLocation(String vendor, String schema) {
		return "classpath:sql/" + vendor + "/" + schema + "-constraints.sql";
	}

	protected List<String> getDataLocations(String vendor, List<String> schemas) {
		List<String> locations = new ArrayList<String>();
		for (String schema : schemas) {
			locations.addAll(getDataLocations(vendor, schema));
		}
		return locations;
	}

	protected List<String> getDataLocations(String vendor, String schema) {
		return LocationUtils.getLocations("classpath:META-INF/" + vendor + "/" + schema + ".tables");
	}
}
