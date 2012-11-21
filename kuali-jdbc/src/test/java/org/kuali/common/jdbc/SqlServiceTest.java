package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.modifier.PropertyModifier;
import org.kuali.common.util.property.modifier.ResolvePlaceholdersModifier;
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
	private DefaultJdbcService sqlExecutor = null;

	@Autowired
	private DefaultJdbcService dbaSqlExecutor = null;

	@Autowired
	private Properties properties = null;

	@Test
	public void testGetLocations() {
		getLocations();
	}

	public List<String> getLocations() {
		Properties props = PropertyUtils.duplicate(properties);
		Properties sql = PropertyUtils.load("classpath:org/kuali/ole/sql.properties", "UTF-8");
		props.putAll(sql);
		PropertyModifier modifier = new ResolvePlaceholdersModifier();
		modifier.modify(props);
		List<String> locations = getLocations(props);
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
			Assert.assertNotNull("sqlExecutor is null.", sqlExecutor);
			Assert.assertNotNull("dbaSqlExecutor is null.", dbaSqlExecutor);
			String dbaUser = properties.getProperty("jdbc.dba.username");
			String dbaUrl = properties.getProperty("jdbc.dba.url");
			String db = properties.getProperty("sql.database");
			logger.info("Validating credentials for user '{}' on [{}]", dbaUser, dbaUrl);
			dbaSqlExecutor.executeString(properties.getProperty("sql.validate"));
			logger.info("Dropping [{}] on [{}]", db, dbaUrl);
			dbaSqlExecutor.executeString(properties.getProperty("sql.dba.drop"));
			logger.info("Creating [{}] on [{}]", db, dbaUrl);
			dbaSqlExecutor.executeString(properties.getProperty("sql.dba.create"));
			String user = properties.getProperty("jdbc.username");
			String url = properties.getProperty("jdbc.url");
			logger.info("Validating credentials for user '{}' on [{}]", user, url);
			sqlExecutor.executeString(properties.getProperty("sql.validate"));

			List<String> locations = getLocations();
			int count = 0;
			for (String location : locations) {
				count += sqlExecutor.executeSQL(location);
			}
			logger.info("Executed {} SQL statements", count);
			logger.info("Elapsed: {}", (System.currentTimeMillis() - start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected List<String> getSchemaLocations(String[] schemas) {
		List<String> schemaLocs = new ArrayList<String>();
		List<String> constraintLocs = new ArrayList<String>();
		for (String schema : schemas) {
			Properties schemaProps = new Properties();
			schemaProps.putAll(properties);
			schemaProps.putAll(PropertyUtils.load("classpath:org/kuali/common/jdbc/schema.properties"));
			schemaProps.setProperty("sql.schema", schema);
			ResolvePlaceholdersModifier modifier = new ResolvePlaceholdersModifier();
			modifier.modify(schemaProps);
			String schemaLocation = schemaProps.getProperty("sql.schema.location");
			String schemaConstraintsLocation = schemaProps.getProperty("sql.schema.location.constraints");
			schemaLocs.add(schemaLocation);
			constraintLocs.add(schemaConstraintsLocation);
		}
		List<String> locs = new ArrayList<String>();
		locs.addAll(schemaLocs);
		locs.addAll(constraintLocs);
		return locs;
	}

	protected List<String> getTableLocations(String base, String tables) {
		List<String> lines = LocationUtils.readLinesFromString(tables);
		List<String> tableLocations = new ArrayList<String>();
		for (String line : lines) {
			String tableLocation = base + "/" + line + ".sql";
			tableLocations.add(tableLocation);
		}
		return tableLocations;
	}
}
