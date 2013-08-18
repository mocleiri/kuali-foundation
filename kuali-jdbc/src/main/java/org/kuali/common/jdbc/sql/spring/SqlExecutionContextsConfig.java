package org.kuali.common.jdbc.sql.spring;

import java.util.List;

import org.kuali.common.jdbc.model.context.SqlExecutionContext;

public interface SqlExecutionContextsConfig {

	List<SqlExecutionContext> sqlExecutionContexts();

}
