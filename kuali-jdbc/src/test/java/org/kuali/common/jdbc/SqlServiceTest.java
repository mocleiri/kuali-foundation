package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
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
	private Properties properties = null;

	@Test
	public void testGetLocations() {
		getLocations();
	}

	public List<String> getLocations() {
		List<String> locations = getLocations(properties);
		for (String location : locations) {
			Assert.assertTrue(LocationUtils.exists(location));
		}
		logger.info("Located {} resources containing SQL", locations.size());
		return locations;
	}

	protected List<String> getLocations(Properties properties) {
		List<String> schemas = PropertyUtils.getStartsWithKeys(properties, "schema.loc");
		List<String> dataLocs = PropertyUtils.getStartsWithKeys(properties, "data.meta");
		List<String> constraints = PropertyUtils.getStartsWithKeys(properties, "constraints.loc");
		List<String> locations = new ArrayList<String>();
		for (String schema : schemas) {
			locations.add(properties.getProperty(schema));
		}
		for (String dataLoc : dataLocs) {
			String location = properties.getProperty(dataLoc);
			List<String> list = LocationUtils.readLines(location);
			locations.addAll(list);
		}
		for (String constraint : constraints) {
			locations.add(properties.getProperty(constraint));
		}
		return locations;
	}

	// @Test
	public void test1() {
		try {
			long start = System.currentTimeMillis();
			logger.info("Jdbc Utils Test");
			// Assert.assertNotNull("sqlExecutor is null.", sqlExecutor);
			// Assert.assertNotNull("dbaSqlExecutor is null.", dbaSqlExecutor);
			String dbaUser = properties.getProperty("jdbc.dba.username");
			String dbaUrl = properties.getProperty("jdbc.dba.url");
			String db = properties.getProperty("sql.database");
			logger.info("Validating credentials for user '{}' on [{}]", dbaUser, dbaUrl);
			// dbaSqlExecutor.executeString(properties.getProperty("sql.validate"));
			logger.info("Dropping [{}] on [{}]", db, dbaUrl);
			// dbaSqlExecutor.executeString(properties.getProperty("sql.dba.drop"));
			logger.info("Creating [{}] on [{}]", db, dbaUrl);
			// dbaSqlExecutor.executeString(properties.getProperty("sql.dba.create"));
			String user = properties.getProperty("jdbc.username");
			String url = properties.getProperty("jdbc.url");
			logger.info("Validating credentials for user '{}' on [{}]", user, url);
			// sqlExecutor.executeString(properties.getProperty("sql.validate"));

			List<String> locations = getLocations();
			int count = 0;
			for (String location : locations) {
				// count += sqlExecutor.executeSQL(location);
			}
			logger.info("Executed {} SQL statements", count);
			logger.info("Elapsed: {}", (System.currentTimeMillis() - start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
