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
public class SqlServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(SqlServiceTest.class);

	@Autowired
	private SqlService service = null;

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
			logger.info("-------- JDBC Information --------");
			logger.info("URL - " + process.getNormal().getUrl());
			logger.info("User - " + process.getNormal().getUsername());
			logger.info("Driver - " + process.getDriver());
			logger.info("DBA URL - " + process.getDba().getUrl());
			logger.info("DBA User - " + process.getDba().getUsername());
			logger.info("----------------------------------");
			doDba(service, dba, dbaSql);
			doSchema(service, normal, properties);
			// doData(service, normal, properties);
			// doConstraints(service, normal, properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doDba(SqlService service, JdbcContext context, List<String> dbaSql) {
		logger.info("Executing DBA SQL");
		SqlMetadata metadata = service.execute(context, dbaSql);
		logExecution("dba", metadata);
	}

	protected void doSchema(SqlService service, JdbcContext context, Properties properties) {
		doDDL(service, context, properties, "schema", "sql.schema.loc");
	}

	protected void doConstraints(SqlService service, JdbcContext context, Properties properties) {
		doDDL(service, context, properties, "constraints", "sql.constraints.loc");
	}

	protected void doData(SqlService service, JdbcContext context, Properties properties) {
		List<String> keys = PropertyUtils.getStartsWithKeys(properties, "sql.data.meta");
		List<String> locationListings = PropertyUtils.getValues(properties, keys);
		List<String> locations = LocationUtils.getLocations(locationListings);
		logger.info("Executing data load SQL");
		SqlMetadata metadata = service.executeLocations(context, locations);
		logExecution("data load", metadata);
	}

	protected SqlMetadata doDDL(SqlService service, JdbcContext context, Properties properties, String type, String prefix) {
		List<String> keys = PropertyUtils.getStartsWithKeys(properties, prefix);
		List<String> locations = PropertyUtils.getValues(properties, keys);
		context.setShow(false);
		logger.info("Executing " + type + " SQL");
		SqlMetadata metadata = service.getLocationsMetadata(context, locations);
		logger.info("Executing {} DDL statements", metadata.getCount());
		context.setShow(true);
		metadata = service.executeLocations(context, locations);
		context.setShow(false);
		logExecution("constraints", metadata);
		return metadata;
	}

	protected List<String> getLocations(List<String> resourceListings) {
		List<String> locations = new ArrayList<String>();
		for (String resourceListing : resourceListings) {
			List<String> lines = LocationUtils.readLines(resourceListing);
			locations.addAll(lines);
		}
		return locations;
	}

	protected void logExecution(String executionType, SqlMetadata metadata) {
		List<Object> args = new ArrayList<Object>();
		args.add(formatter.getCount(metadata.getCount()));
		args.add(formatter.getCount(metadata.getSourceMetadata().size()));
		args.add(formatter.getTime(metadata.getExecutionTime()));
		logger.info("Total " + executionType + " SQL statements: {}  Sources: {}  Total time: {}", CollectionUtils.toArray(args));
	}

}
