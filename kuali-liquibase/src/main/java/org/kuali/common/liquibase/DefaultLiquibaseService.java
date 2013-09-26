package org.kuali.common.liquibase;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ResourceAccessor;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.service.JdbcUtils;
import org.kuali.common.util.CollectionUtils;

public class DefaultLiquibaseService implements LiquibaseService {

	@Override
	public void update(LiquibaseContext context) {

		// Convert the list of contexts to CSV (if there are any)
		String csv = StringUtils.trimToNull(CollectionUtils.asCSV(context.getContexts()));

		// Get a handle to the datasource from the context
		DataSource dataSource = context.getDataSource();

		// Setup our connection object
		Connection connection = null;
		try {
			// Get a connection to the db from the DataSource
			connection = dataSource.getConnection();

			// Get a Liquibase object from the connection and context
			Liquibase liquibase = getLiquibase(context, connection);

			// Invoke liquibase to update the database
			liquibase.update(csv);
		} catch (SQLException e) {
			throw new IllegalStateException("Unexpected SQL error", e);
		} catch (LiquibaseException e) {
			throw new IllegalStateException("Unexpected Liquibase error", e);
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error", e);
		} finally {
			// Make sure the database connection always gets closed no matter what
			JdbcUtils.closeQuietly(dataSource, connection);
		}
	}

	/**
	 * Use Liquibase's <code>DatabaseFactory</code> to get a handle to the desired <code>Database</code> implementation and otherwise setup a <code>Liquibase</code> object.
	 */
	protected Liquibase getLiquibase(LiquibaseContext context, Connection conn) throws LiquibaseException {
		DatabaseFactory factory = DatabaseFactory.getInstance();
		JdbcConnection jdbcConnection = new JdbcConnection(conn);
		Database database = factory.findCorrectDatabaseImplementation(jdbcConnection);
		String changeLog = context.getChangeLog();
		ResourceAccessor resourceAccessor = new DefaultResourceAccessor();
		return new Liquibase(changeLog, resourceAccessor, database);
	}
}
