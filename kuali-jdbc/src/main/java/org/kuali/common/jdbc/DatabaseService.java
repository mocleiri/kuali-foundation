package org.kuali.common.jdbc;

import org.kuali.common.jdbc.context.DatabaseInitializeContext;

public interface DatabaseService {

	void initialize(DatabaseInitializeContext context);

}
