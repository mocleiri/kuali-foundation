package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.SimpleFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JdbcServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(JdbcServiceTest.class);

	@Autowired
	private JdbcService service = null;

	@Autowired
	private String encoding = null;

	@Autowired
	private DatabaseProcessContext process = null;

	@Autowired
	private Properties properties = null;

	@Autowired
	private JdbcContext normal = null;

	@Autowired
	private JdbcContext dba = null;

	@Resource(name = "dbaSql")
	private List<String> dbaSql = null;

	@Autowired
	private SimpleFormatter formatter = null;

	@Test
	public void testOLEDatabaseProcess() {
		try {
			logger.info("---------------- JDBC Information ----------------");
			logger.info("Vendor - {}", process.getVendor());
			logger.info("URL - {}", process.getUrl());
			logger.info("User - {}", process.getUsername());
			logger.debug("Password - {}", process.getPassword());
			logger.info("Driver - {}", process.getDriver());
			logger.info("DBA URL - {}", process.getDbaUrl());
			logger.info("DBA User - {}", process.getDbaUsername());
			logger.debug("DBA Password - {}", process.getDbaPassword());
			JdbcMetaData metadata = service.getJdbcMetaData(dba.getDataSource());
			logger.info("Product Name - {}", metadata.getDatabaseProductName());
			logger.info("Product Version - {}", metadata.getDatabaseProductVersion());
			logger.info("Driver Name - {}", metadata.getDriverName());
			logger.info("Driver Version - {}", metadata.getDriverVersion());
			logger.info("SQL Encoding - {}", encoding);
			logger.info("--------------------------------------------------");
			doDba(service, dba, dbaSql);
			// doSchema(service, normal, properties);
			// doData(service, normal, properties);
			// doConstraints(service, normal, properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doDba(JdbcService service, JdbcContext context, List<String> dbaSql) {
		logger.info("Executing DBA SQL");
		SqlMetaDataList metadata = service.executeSqlStrings(context, dbaSql);
		logExecution("dba", metadata);
	}

	protected void doSchema(JdbcService service, JdbcContext context, Properties properties, String encoding) {
		doDDL(service, context, properties, "schema", "sql.schema.loc", encoding);
	}

	protected void doConstraints(JdbcService service, JdbcContext context, Properties properties, String encoding) {
		doDDL(service, context, properties, "constraints", "sql.constraints.loc", encoding);
	}

	protected void doData(JdbcService service, JdbcContext context, Properties properties) {
		List<String> keys = PropertyUtils.getStartsWithKeys(properties, "sql.data.meta");
		List<String> locationListings = PropertyUtils.getValues(properties, keys);
		List<String> locations = LocationUtils.getLocations(locationListings);
		logger.info("Executing data load SQL");
		SqlMetaDataList metadata = service.executeSql(context, locations);
		logExecution("data load", metadata);
	}

	protected SqlMetaDataList doDDL(JdbcService service, JdbcContext context, Properties properties, String type, String prefix, String encoding) {
		List<String> keys = PropertyUtils.getStartsWithKeys(properties, prefix);
		List<String> locations = PropertyUtils.getValues(properties, keys);
		logger.info("Executing " + type + " SQL");
		SqlMetaDataList metadata = service.getMetaData(context, locations, encoding);
		logger.info("Executing {} DDL statements", metadata.getCount());
		metadata = service.executeSql(context, locations);
		logExecution("constraints", metadata);
		return metadata;
	}

	protected void logExecution(String executionType, SqlMetaDataList metadata) {
		List<Object> args = new ArrayList<Object>();
		args.add(formatter.getCount(metadata.getCount()));
		args.add(formatter.getCount(metadata.size()));
		args.add(formatter.getTime(metadata.getExecutionTime()));
		logger.info("Total " + executionType + " SQL statements: {}  Sources: {}  Total time: {}", CollectionUtils.toArray(args));
	}

}
