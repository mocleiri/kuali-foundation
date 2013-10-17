package org.kuali.common.jdbc.sql.spring;

import java.util.List;

import org.kuali.common.jdbc.model.context.JdbcContext;

public interface JdbcContextsConfig {

	List<JdbcContext> jdbcContexts();

}
