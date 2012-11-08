package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.ResourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SqlExecutorTest {

	private static final Logger logger = LoggerFactory.getLogger(SqlExecutorTest.class);

	@Autowired
	private SqlExecutor sqlExecutor = null;

	@Autowired
	private SqlExecutor dbaSqlExecutor = null;

	@Autowired
	private Properties properties = null;

	@Autowired
	private String tables = null;

	@Test
	public void test() {
		try {
			long start = System.currentTimeMillis();
			logger.info("Jdbc Utils Test");
			String base = properties.getProperty("sql.location.base");
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
			String schema = properties.getProperty("sql.source.schema");
			String schemaLocation = properties.getProperty("sql.schema");
			String schemaConstraintsLocation = properties.getProperty("sql.schema.constraints");
			List<String> locations = getTableLocations(base, tables);
			logger.info("Executing SQL for [{}] on [{}] at [" + url + "]", schema, db);
			int count = sqlExecutor.executeSQL(schemaLocation);
			for (String location : locations) {
				count += sqlExecutor.executeSQL(location);
			}
			count += sqlExecutor.executeSQL(schemaConstraintsLocation);
			logger.info("Executed {} SQL statements", count);
			logger.info("Elapsed: {}", (System.currentTimeMillis() - start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected List<String> getTableLocations(String base, String tables) {
		List<String> lines = ResourceUtils.readLinesFromString(tables);
		List<String> tableLocations = new ArrayList<String>();
		for (String line : lines) {
			String tableLocation = base + "/" + line + ".sql";
			tableLocations.add(tableLocation);
		}
		return tableLocations;
	}
}
