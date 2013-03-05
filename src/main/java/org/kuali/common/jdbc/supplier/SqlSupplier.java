package org.kuali.common.jdbc.supplier;

import org.kuali.common.jdbc.SqlMetaData;

public interface SqlSupplier {

	/**
	 * Open the supplier so it is ready to provide SQL
	 */
	void open();

	/**
	 * Return a single SQL statement to execute. Returns <code>null</code> when the SQL supply has been exhausted.
	 */
	String getSql();

	/**
	 * Close the supplier to free any resources it may have open
	 */
	void close();

	/**
	 * Return SQL count and overall size
	 */
	SqlMetaData getSqlMetaData();

}
