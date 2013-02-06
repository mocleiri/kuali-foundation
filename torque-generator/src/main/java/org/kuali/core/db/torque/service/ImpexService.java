package org.kuali.core.db.torque.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.torque.engine.platform.Platform;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.core.db.torque.StringFilter;
import org.kuali.core.db.torque.pojo.DatabaseContext;
import org.kuali.core.db.torque.pojo.Sequence;
import org.kuali.core.db.torque.pojo.TableContext;
import org.kuali.core.db.torque.pojo.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class ImpexService {

	private static final Logger logger = LoggerFactory.getLogger(ImpexService.class);

	/**
	 * Use jdbc to obtain the list of tables/views/sequences for a given schema
	 */
	public DatabaseContext getDatabaseObjectLists(ImpexContext context) throws SQLException {
		DatabaseContext database = new DatabaseContext();

		// Add in tables and views
		long start = System.currentTimeMillis();
		// fillInDatabaseMetaData(database, context);
		logger.info("Database object lists created.  Time: {}", FormatUtils.getTime(System.currentTimeMillis() - start));

		return database;
	}

	protected void doFilter(Collection<String> elements, List<String> includes, List<String> excludes, String label) {
		int all = elements.size();
		StringFilter filterer = new StringFilter(includes, excludes);
		filterer.filter(elements.iterator());
		int remaining = elements.size();
		int diff = all - remaining;
		Object[] args = { StringUtils.rightPad(label, 12, " "), lpad(all), lpad(diff), lpad(remaining) };
		logger.info("{} - {}, filtered out - {}, remaining - {}", args);
	}

	// Left pad numbers with 4 digits or less
	protected String lpad(int smallNumber) {
		return StringUtils.leftPad(smallNumber + "", 4, " ");
	}

	/**
	 * Connect to a database and retrieve a list of all the tables for a given schema.
	 */
	protected List<TableContext> getTableList(DatabaseMetaData metaData, Platform platform, ImpexContext context) throws SQLException {
		List<String> tables = platform.getTableNames(metaData, context.getSchemaName());
		doFilter(tables, context.getTableIncludes(), context.getTableExcludes(), "tables");
		return getTableContexts(tables);
	}

	/**
	 * Connect to a database and retrieve tables/views
	 */
	protected void fillInDatabase(DatabaseContext database, ImpexContext context) throws SQLException {
		logger.info("Opening database connection.");

		Connection conn = null;
		try {
			// Connect to the database
			conn = DataSourceUtils.getConnection(context.getDataSource());

			logger.info("Generating database object lists.");

			// Extract JDBC's metadata object
			DatabaseMetaData metaData = conn.getMetaData();

			// Convert JDBC metadata into a list of tables names
			if (context.isProcessTables()) {
				List<TableContext> tables = getTableList(metaData, context.getPlatform(), context);
				database.setTables(tables);
			}

			// Convert JDBC metadata into a list of view names
			if (context.isProcessViews()) {
				List<String> names = getViewNames(metaData, context);
				List<View> views = getViews(names);
				database.setViews(views);
			}

			// Acquire a list of sequence names (invokes platform specific logic)
			if (context.isProcessSequences()) {
				List<String> names = getSequences(database, metaData, context);
				List<Sequence> sequences = getSequences(names);
				database.setSequences(sequences);
			}
		} finally {
			// Close the connection
			closeQuietly(conn, context.getDataSource());
		}
	}

	protected List<Sequence> getSequences(List<String> names) {
		List<Sequence> sequences = new ArrayList<Sequence>();
		for (String name : names) {
			Sequence sequence = new Sequence();
			sequence.setName(name);
			sequences.add(sequence);
		}
		return sequences;
	}

	protected List<String> getSequences(DatabaseContext database, DatabaseMetaData metaData, ImpexContext context) throws SQLException {
		List<String> sequences = context.getPlatform().getSequenceNames(metaData, context.getSchemaName());
		doFilter(sequences, context.getSequenceIncludes(), context.getSequenceExcludes(), "sequences");
		return sequences;
	}

	public void closeQuietly(Connection conn, DataSource dataSource) {
		if (conn != null) {
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

	public List<String> getViewNames(DatabaseMetaData metaData, ImpexContext context) throws SQLException {
		List<String> views = new ArrayList<String>();
		ResultSet tableNames = null;
		// these are the entity types we want from the database
		String[] types = { "VIEW" }; // JHK: removed views from list
		try {
			tableNames = metaData.getTables(null, context.getSchemaName(), null, types);
			while (tableNames.next()) {
				String name = tableNames.getString(3);
				views.add(name);
			}
		} finally {
			if (tableNames != null) {
				tableNames.close();
			}
		}
		doFilter(views, context.getViewIncludes(), context.getViewExcludes(), "views");
		return views;
	}

	protected List<TableContext> getTableContexts(List<String> tables) {
		List<TableContext> contexts = new ArrayList<TableContext>();
		for (int i = 0; i < tables.size(); i++) {
			TableContext context = new TableContext();
			context.setSequence(i + 1);
			context.setName(tables.get(i));
			contexts.add(context);
		}
		return contexts;
	}

	protected List<View> getViews(List<String> names) {
		List<View> views = new ArrayList<View>();
		for (String name : names) {
			View view = new View();
			view.setName(name);
			views.add(view);
		}
		return views;
	}

}
