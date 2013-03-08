package org.kuali.common.jdbc.supplier;

import java.io.IOException;
import java.util.List;

import org.kuali.common.jdbc.SqlMetaData;

public interface SqlSupplier extends Comparable<SqlSupplier> {

	/**
	 * Open the supplier so it is ready to provide SQL
	 */
	void open() throws IOException;

	/**
	 * Return a list of SQL statements to execute. Returns <code>null</code> when the SQL supply has been exhausted.
	 */
	List<String> getSql() throws IOException;

	/**
	 * Close the supplier to free any resources it may have opened
	 */
	void close();

	/**
	 * Fill in SQL count + overall size
	 */
	void fillInMetaData();

	/**
	 * Return SQL count and overall size
	 */
	SqlMetaData getMetaData();

}
