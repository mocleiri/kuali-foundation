package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
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
			logger.info("DBA URL - " + process.getDba().getUrl());
			logger.info("DBA User - " + process.getDba().getUsername());
			logger.info("Driver - " + process.getDriver());
			logger.info("URL - " + process.getNormal().getUrl());
			logger.info("User - " + process.getNormal().getUsername());
			logger.info("----------------------------------");
			doDba(service, dba, dbaSql);
			doSchema(service, normal, properties);
			doData(service, normal, properties);
			doConstraints(service, normal, properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doDba(SqlService service, JdbcContext context, List<String> dbaSql) {
		SqlMetadata metadata = service.execute(context, dbaSql);
		logger.info("Executed {} dba sql statements", metadata.getCount());
	}

	protected void doSchema(SqlService service, JdbcContext context, Properties properties) {
		logger.info("Executing schema SQL");
		long start = System.currentTimeMillis();
		SqlMetadata metadata = doPrefix(service, context, properties, "sql.schema.loc");
		long elapsed = System.currentTimeMillis() - start;
		Object[] args = new Object[] { formatter.getCount(metadata.getCount()), formatter.getCount(metadata.getSourceMetadata().size()), formatter.getTime(elapsed) };
		logger.info("Executed {} schema SQL statements from {} sources.  Total time: {}", args);
	}

	protected void doConstraints(SqlService service, JdbcContext context, Properties properties) {
		logger.info("Executing constraints SQL");
		long start = System.currentTimeMillis();
		SqlMetadata metadata = doPrefix(service, context, properties, "sql.constraints.loc");
		long elapsed = System.currentTimeMillis() - start;
		Object[] args = new Object[] { formatter.getCount(metadata.getCount()), formatter.getCount(metadata.getSourceMetadata().size()), formatter.getTime(elapsed) };
		logger.info("Executed {} constraints SQL statements from {} sources.  Total time: {}", args);
	}

	protected void doData(SqlService service, JdbcContext context, Properties properties) {
		List<String> keys = PropertyUtils.getStartsWithKeys(properties, "sql.data.meta");
		List<String> resourceListings = PropertyUtils.getValues(properties, keys);
		List<String> locations = getLocations(resourceListings);
		logger.info("Executing data load SQL");
		long start = System.currentTimeMillis();
		SqlMetadata metadata = service.executeLocations(context, locations);
		long elapsed = System.currentTimeMillis() - start;
		Object[] args = new Object[] { formatter.getCount(metadata.getCount()), formatter.getCount(metadata.getSourceMetadata().size()), formatter.getTime(elapsed) };
		logger.info("Executed {} data load SQL statements from {} sources.  Total time: {}", args);
	}

	protected SqlMetadata doPrefix(SqlService service, JdbcContext context, Properties properties, String prefix) {
		List<String> keys = PropertyUtils.getStartsWithKeys(properties, prefix);
		List<String> locations = PropertyUtils.getValues(properties, keys);
		return service.executeLocations(context, locations);
	}

	protected List<String> getLocations(List<String> resourceListings) {
		List<String> locations = new ArrayList<String>();
		for (String resourceListing : resourceListings) {
			List<String> lines = LocationUtils.readLines(resourceListing);
			locations.addAll(lines);
		}
		return locations;
	}

}
