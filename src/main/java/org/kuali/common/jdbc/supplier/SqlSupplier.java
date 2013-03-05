package org.kuali.common.jdbc.supplier;

import java.io.IOException;

import org.kuali.common.jdbc.SqlMetaData;

public interface SqlSupplier {

	/**
	 * Open the supplier so it is ready to provide SQL
	 */
	void open() throws IOException;

	/**
	 * Return a single SQL statement to execute. Returns <code>null</code> when the SQL supply has been exhausted.
	 */
	String getSql() throws IOException;

	/**
	 * Close the supplier to free any resources it may have open
	 */
	void close();

	/**
	 * Return SQL count and overall size
	 */
	SqlMetaData getSqlMetaData();

}
