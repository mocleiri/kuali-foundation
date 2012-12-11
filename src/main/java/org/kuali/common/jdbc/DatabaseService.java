package org.kuali.common.jdbc;

import org.kuali.common.jdbc.context.DatabaseResetContext;

public interface DatabaseService {

	void reset(DatabaseResetContext context);

}
