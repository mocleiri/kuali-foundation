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

	@Autowired
	private JdbcContext normal = null;

	@Autowired
	private JdbcContext dba = null;

	@Test
	public void testOLEDatabaseProcess() {
		List<String> sql = getSql();
		List<SqlSource> dbaSql = JdbcUtils.getStringSqlSources(sql);
		SqlMetadata metadata = service.getSqlMetadata(dba, dbaSql);
		logger.info("Located {} dba sql statements to execute", metadata.getCount());
		long executed = service.executeSql(dba, dbaSql);
		logger.info("Executed {} dba sql statements", executed);
		List<String> locations = getLocations();
		List<SqlSource> sources = JdbcUtils.getLocationSqlSources(locations);
		long start = System.currentTimeMillis();
		logger.info("Examining {} locations for SQL to execute", sources.size());
		metadata = service.getSqlMetadata(normal, sources);
		long elapsed = System.currentTimeMillis() - start;
		logger.info("Located {} SQL statements in {} millis", metadata.getCount(), elapsed);
		start = System.currentTimeMillis();
		executed = service.executeSql(normal, sources);
		elapsed = System.currentTimeMillis() - start;
		logger.info("Executed {} sql statements in {} millis", executed, elapsed);
	}

	protected List<String> getSql() {
		List<String> sql = new ArrayList<String>();
		sql.add(properties.getProperty("sql.validate"));
		sql.add(properties.getProperty("sql.dba.drop"));
		sql.add(properties.getProperty("sql.dba.create"));
		return sql;
	}

	protected List<String> getLocations() {
		List<String> locations = getLocations(properties);
		for (String location : locations) {
			Assert.assertTrue(LocationUtils.exists(location));
		}
		logger.info("Located {} resources containing SQL", locations.size());
		return locations;
	}

	protected List<String> getLocations(Properties properties) {
		List<String> schemas = PropertyUtils.getStartsWithKeys(properties, "sql.schema.loc");
		List<String> dataLocs = PropertyUtils.getStartsWithKeys(properties, "sql.data.meta");
		List<String> constraints = PropertyUtils.getStartsWithKeys(properties, "sql.constraints.loc");
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

}
