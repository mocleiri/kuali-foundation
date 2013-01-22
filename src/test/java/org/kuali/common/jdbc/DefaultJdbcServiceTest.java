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
	String vendor = System.getProperty("db.vendor") == null ? "mysql" : System.getProperty("db.vendor");
	String dataThreads = System.getProperty("data.threads") == null ? "5" : System.getProperty("data.threads");
	boolean mysqlRice = Boolean.getBoolean("mysql.rice");
	Properties properties = getProperties();
	JdbcContext jdbcDba = getJdbcDba();
	JdbcContext jdbcContext = getJdbc();

	protected Properties getProperties() {
		Properties sql1 = PropertyUtils.load("classpath:org/kuali/common/sql/mysql.xml");
		Properties sql2 = PropertyUtils.load("classpath:org/kuali/common/sql/oracle.xml");
		Properties jdbc1 = PropertyUtils.load("classpath:org/kuali/common/jdbc/jdbc.properties");
		Properties jdbc2 = PropertyUtils.load("classpath:org/kuali/common/deploy/jdbc.properties");
		Properties ole = PropertyUtils.load("classpath:ole-fs.properties");
		Properties properties = PropertyUtils.combine(sql1, sql2, jdbc1, jdbc2, ole);
		properties.setProperty("db.vendor", vendor);
		properties.setProperty("jdbc.username", "JDBCTEST");
		properties.setProperty("oracle.dba.url", "jdbc:oracle:thin:@oraperf.ks.kuali.org:1521:ORAPERF");
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
		String username = getValue("jdbc.username");
		String password = getValue("jdbc.password");

		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
		dataSource.setDriverClassName(driver);

		JdbcContext context = new JdbcContext();
		context.setDataSource(dataSource);
		return context;
	}

	protected ExecutionContext getDbaContext() {
		ExecutionContext ec = new ExecutionContext();
		ec.setMessage("Executing DBA SQL");
		ec.setJdbcContext(jdbcDba);
		ec.setReader(reader);
		ec.setSql(Arrays.asList(getValue("sql.drop"), getValue("sql.create")));
		ec.setListener(getDbaListener());
		return ec;
	}

	protected ExecutionContext getSequentialDMLContext(List<String> keys) {
		return getThreadSafeDMLContext(keys, 1);
	}

	protected ExecutionContext getThreadSafeDMLContext(List<String> keys, int threads) {
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

	protected ExecutionContext getThreadSafeDDLContext(Properties properties) {

		String prefix = "sql.schema";

		ExecutionContext ec = new ExecutionContext();
		ec.setJdbcContext(jdbcContext);
		ec.setReader(reader);
		// ec.setLocations(getLocations(key));
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
			ExecutionContext schemas = getThreadSafeDDLContext(properties);
			schemas.setMessage("Executing schema DDL");
			ExecutionContext data1 = getThreadSafeDMLContext(Arrays.asList("sql.data.loc.list.1", "sql.data.loc.list.2"), threads);
			data1.setMessage("Executing concurrent DML");
			ExecutionContext data2 = getSequentialDMLContext(Arrays.asList("sql.data.loc.list.3"));
			data2.setMessage("Executing sequential DML");
			ExecutionContext constraints = getThreadSafeDDLContext("sql.constraints.loc");
			constraints.setMessage("Executing constraints DDL");

			List<ExecutionContext> contexts = Arrays.asList(dba, schemas, data1, data2, constraints);

			boolean skip = Boolean.getBoolean("sql.skip") || true;

			long start = System.currentTimeMillis();
			JdbcService service = new DefaultJdbcService();
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
}
