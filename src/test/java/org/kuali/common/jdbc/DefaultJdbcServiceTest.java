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
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultJdbcServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultJdbcServiceTest.class);
	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	SqlReader reader = new DefaultSqlReader();
	String vendor = System.getProperty("db.vendor") == null ? "oracle" : System.getProperty("db.vendor");
	String dataThreads = System.getProperty("data.threads") == null ? "1" : System.getProperty("data.threads");
	Properties properties = getOleProperties();
	JdbcContext jdbcDba = getJdbcDba();
	JdbcContext jdbcContext = getJdbc();

	protected Properties getProperties() {
		Properties sql1 = PropertyUtils.load("classpath:org/kuali/common/sql/mysql.xml");
		Properties sql2 = PropertyUtils.load("classpath:org/kuali/common/sql/oracle.xml");
		Properties jdbc1 = PropertyUtils.load("classpath:org/kuali/common/jdbc/jdbc.properties");
		Properties jdbc2 = PropertyUtils.load("classpath:org/kuali/common/deploy/jdbc.properties");
		Properties properties = PropertyUtils.combine(sql1, sql2, jdbc1, jdbc2);
		properties.setProperty("db.vendor", vendor);
		properties.setProperty("jdbc.username", "JDBCTEST");
		return properties;
	}

	protected Properties getOleProperties() {
		Properties sql1 = PropertyUtils.load("classpath:org/kuali/common/sql/mysql.xml");
		Properties sql2 = PropertyUtils.load("classpath:org/kuali/common/sql/oracle.xml");
		Properties jdbc1 = PropertyUtils.load("classpath:org/kuali/common/jdbc/jdbc.properties");
		Properties jdbc2 = PropertyUtils.load("classpath:org/kuali/common/deploy/jdbc.properties");
		Properties ole = PropertyUtils.load("classpath:org/kuali/ole/ole-fs.properties");
		Properties properties = PropertyUtils.combine(sql1, sql2, jdbc1, jdbc2, ole);
		properties.setProperty("db.vendor", vendor);
		properties.setProperty("jdbc.username", "JDBCTEST");
		properties.setProperty("oracle.dba.url", "jdbc:oracle:thin:@oraperf.ks.kuali.org:1521:ORAPERF");
		properties.setProperty("oracle.dba.username", "master");
		properties.setProperty("oracle.dba.password", "gw570229");
		// properties.setProperty("mysql.dba.url", "jdbc:mysql://mysql.rice.kuali.org");
		// properties.setProperty("mysql.dba.username", "master");
		// properties.setProperty("mysql.dba.password", "gw570229");
		properties.setProperty("mysql.dba.url", "jdbc:mysql://localhost");
		properties.setProperty("mysql.dba.username", "root");
		properties.setProperty("mysql.dba.password", "NONE");
		return properties;
	}

	protected String getValue(String key) {
		String original = properties.getProperty(key);
		String resolved = helper.replacePlaceholders(original, properties);
		if (NullUtils.isNullOrNone(resolved)) {
			return null;
		} else {
			return resolved;
		}
	}

	protected JdbcContext getJdbcDba() {
		String url = getValue("jdbc.dba.url");
		String driver = getValue("jdbc.driver");
		String username = getValue("jdbc.dba.username");
		String password = getValue("jdbc.dba.password");
		JdbcContext context = new JdbcContext();
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
		dataSource.setDriverClassName(driver);
		context.setDataSource(dataSource);
		return context;
	}

	protected JdbcContext getJdbc() {
		String url = getValue("jdbc.url");
		String driver = getValue("jdbc.driver");
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, getValue("jdbc.username"), getValue("jdbc.password"));
		JdbcContext context = new JdbcContext();
		dataSource.setDriverClassName(driver);
		context.setDataSource(dataSource);
		return context;
	}

	protected ExecutionContext getDbaContext() {
		ExecutionContext ec = new ExecutionContext();
		ec.setJdbcContext(jdbcDba);
		ec.setReader(reader);
		ec.setSql(Arrays.asList(getValue("sql.drop"), getValue("sql.create")));
		ec.setListener(getDbaListener());
		return ec;
	}

	protected ExecutionContext getSequentialDataContext(List<String> keys) {
		return getThreadSafeDataContext(keys, 1);
	}

	protected ExecutionContext getThreadSafeDataContext(List<String> keys, int threads) {
		ExecutionContext ec = new ExecutionContext();
		ec.setJdbcContext(jdbcContext);
		ec.setReader(reader);
		List<String> locations = new ArrayList<String>();
		for (String key : keys) {
			locations.addAll(getLocations(key));
		}
		ec.setLocations(locations);
		ec.setThreads(threads);
		ec.setListener(getDefaultListener());
		return ec;
	}

	protected ExecutionContext getThreadSafeDMLContext(String key) {
		ExecutionContext ec = new ExecutionContext();
		ec.setJdbcContext(jdbcContext);
		ec.setReader(reader);
		ec.setLocations(getLocations(key));
		ec.setThreads(ec.getLocations().size());
		ec.setListener(getDefaultListener());
		return ec;
	}

	protected NotifyingListener getDefaultListener() {
		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new ProgressListener());
		listeners.add(new SummaryListener());
		return new NotifyingListener(listeners);
	}

	protected NotifyingListener getDbaListener() {
		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new LogSqlListener());
		listeners.add(new SummaryListener());
		return new NotifyingListener(listeners);
	}

	@Test
	public void testReset() {
		try {

			logger.info(getValue("jdbc.url"));
			logger.info(getValue("jdbc.dba.url"));
			logger.info(getValue("jdbc.username"));
			logger.info(getValue("jdbc.password"));
			logger.info(getValue("jdbc.dba.username"));
			logger.info(getValue("jdbc.dba.password"));

			int threads = new Integer(dataThreads);

			ExecutionContext dba = getDbaContext();
			ExecutionContext schemas = getThreadSafeDMLContext("sql.schema.loc");
			ExecutionContext data1 = getThreadSafeDataContext(Arrays.asList("sql.data.loc.list.1", "sql.data.loc.list.2"), threads);
			ExecutionContext data2 = getSequentialDataContext(Arrays.asList("sql.data.loc.list.3"));
			ExecutionContext constraints = getThreadSafeDMLContext("sql.constraints.loc");

			List<ExecutionContext> contexts = Arrays.asList(schemas, data1, data2, constraints);

			boolean skip = Boolean.getBoolean("sql.skip");

			long start = System.currentTimeMillis();
			JdbcService service = new DefaultJdbcService();
			service.executeSql(dba);
			for (ExecutionContext context : contexts) {
				if (skip) {
					context.setExecute(false);
				}
				service.executeSql(context);
			}
			String time = FormatUtils.getTime(System.currentTimeMillis() - start);
			logger.info("Total time: {}", time);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected List<String> getLocations(String prefix) {
		List<String> keys = PropertyUtils.getStartsWithKeys(properties, prefix);
		List<String> locations = new ArrayList<String>();
		for (String key : keys) {
			String value = getValue(key);
			if (StringUtils.contains(key, ".loc.list")) {
				locations.addAll(LocationUtils.getLocations(value));
			} else {
				locations.add(value);
			}
		}
		return locations;
	}

	protected List<String> getSchemaLocations(String vendor, List<String> schemas) {
		List<String> locations = new ArrayList<String>();
		for (String schema : schemas) {
			locations.add(getSchemaLocation(vendor, schema));
		}
		return locations;
	}

	protected List<String> getConstraintsLocations(String vendor, List<String> schemas) {
		List<String> locations = new ArrayList<String>();
		for (String schema : schemas) {
			locations.add(getConstraintsLocation(vendor, schema));
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
