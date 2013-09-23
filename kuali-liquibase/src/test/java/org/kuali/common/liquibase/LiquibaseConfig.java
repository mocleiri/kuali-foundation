package org.kuali.common.liquibase;

import liquibase.integration.spring.SpringLiquibase;

public interface LiquibaseConfig {

	SpringLiquibase springLiquibase();

}
