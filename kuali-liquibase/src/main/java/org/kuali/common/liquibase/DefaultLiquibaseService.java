package org.kuali.common.liquibase;

import java.sql.Connection;
import java.sql.SQLException;

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
		String csv = StringUtils.trimToNull(CollectionUtils.asCSV(context.getContexts()));
		Connection connection = null;
		Liquibase liquibase = null;
		try {
			connection = context.getDataSource().getConnection();
			liquibase = createLiquibase(context, connection);
			liquibase.update(csv);
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		} catch (LiquibaseException e) {
			throw new IllegalStateException(e);
		} finally {
			JdbcUtils.closeQuietly(context.getDataSource(), connection);
		}
	}

	protected Liquibase createLiquibase(LiquibaseContext context, Connection conn) throws LiquibaseException {
		DatabaseFactory factory = DatabaseFactory.getInstance();
		JdbcConnection jdbcConnection = new JdbcConnection(conn);
		Database database = factory.findCorrectDatabaseImplementation(jdbcConnection);
		String changeLog = context.getChangeLog();
		ResourceAccessor resourceAccessor = new DefaultResourceAccessor();
		return new Liquibase(changeLog, resourceAccessor, database);
	}
}
