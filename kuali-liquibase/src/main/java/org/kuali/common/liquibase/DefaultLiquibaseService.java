package org.kuali.common.liquibase;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

import org.kuali.common.util.CollectionUtils;

public class DefaultLiquibaseService implements LiquibaseService {

	@Override
	public void update(LiquibaseContext context) {
		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setChangeLog(context.getChangeLog());
		springLiquibase.setDataSource(context.getDataSource());
		springLiquibase.setContexts(CollectionUtils.asCSV(context.getContexts()));
		try {
			springLiquibase.afterPropertiesSet();
		} catch (LiquibaseException e) {
			throw new IllegalStateException("Unexpected Liquibase error", e);
		}
	}

}
