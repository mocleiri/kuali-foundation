package org.kuali.common.jdbc.supplier;

import org.kuali.common.jdbc.SqlMetaData;

public interface SqlSupplier {

	/**
	 * Return a single SQL statement to execute. Return <code>null</code> when the SQL supply has been exhausted.
	 */
	String getSql();

	/**
	 * Return SQL count and overall size
	 */
	SqlMetaData getSqlMetaData();

}
