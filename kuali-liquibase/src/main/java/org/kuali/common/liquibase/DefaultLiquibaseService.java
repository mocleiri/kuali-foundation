package org.kuali.common.liquibase;

import java.sql.Connection;
import java.sql.SQLException;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.service.JdbcUtils;
import org.kuali.common.util.CollectionUtils;
import org.springframework.core.io.DefaultResourceLoader;

public class DefaultLiquibaseService implements LiquibaseService {

	@Override
	public void update(LiquibaseContext context) {
		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setChangeLog(context.getChangeLog());
		springLiquibase.setDataSource(context.getDataSource());
		springLiquibase.setContexts(CollectionUtils.asCSV(context.getContexts()));
		springLiquibase.setResourceLoader(new DefaultResourceLoader());
		try {
			springLiquibase.afterPropertiesSet();
		} catch (LiquibaseException e) {
			throw new IllegalStateException("Unexpected Liquibase error", e);
		}
	}

	protected void foo(LiquibaseContext context) {
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
		return new Liquibase(changeLog, createResourceOpener(), database);
	}
}
