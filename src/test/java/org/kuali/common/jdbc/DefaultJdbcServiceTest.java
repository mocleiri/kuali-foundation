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
	String vendor = System.getProperty("db.vendor") == null ? "oracle" : System.getProperty("db.vendor");
	String dataThreads = System.getProperty("data.threads") == null ? "1" : System.getProperty("data.threads");
	List<String> schemas = Arrays.asList("rice-impex-server-bootstrap");
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
		properties.setProperty("mysql.dba.url", "jdbc:mysql://mysql.rice.kuali.org");
		properties.setProperty("mysql.dba.username", "master");
		properties.setProperty("mysql.dba.password", "gw570229");
		return properties;
	}

	protected String getValue(String key) {
		String value = properties.getProperty(key);
		return helper.replacePlaceholders(value, properties);
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

	protected ExecutionContext getSchemasContext() {
		ExecutionContext ec = new ExecutionContext();
		ec.setJdbcContext(jdbcContext);
		ec.setReader(reader);
		// ec.setLocations(getSchemaLocations(vendor, schemas));
		ec.setLocations(getLocations("sql.schema.loc"));
		ec.setThreads(ec.getLocations().size());
		ec.setListener(getDefaultListener());
		return ec;
	}

	protected ExecutionContext getDataContext() {
		ExecutionContext ec = new ExecutionContext();
		ec.setJdbcContext(jdbcContext);
		ec.setReader(reader);
		// ec.setLocations(getDataLocations(vendor, schemas));
		ec.setLocations(getLocations("sql.data.loc"));
		ec.setThreads(10);
		ec.setListener(getDefaultListener());
		return ec;
	}

	protected ExecutionContext getConstraintsContext() {
		ExecutionContext ec = new ExecutionContext();
		ec.setJdbcContext(jdbcContext);
		ec.setReader(reader);
		// ec.setLocations(getConstraintsLocations(vendor, schemas));
		ec.setLocations(getLocations("sql.constraints.loc"));
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

			ExecutionContext dba = getDbaContext();
			ExecutionContext schemas = getSchemasContext();
			ExecutionContext data = getDataContext();
			ExecutionContext constraints = getConstraintsContext();

			boolean execute = true;

			dba.setExecute(execute);
			schemas.setExecute(execute);
			data.setExecute(execute);
			data.setThreads(new Integer(dataThreads));
			constraints.setExecute(false);

			long start = System.currentTimeMillis();
			JdbcService service = new DefaultJdbcService();
			service.executeSql(dba);
			service.executeSql(schemas);
			service.executeSql(data);
			service.executeSql(constraints);
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
