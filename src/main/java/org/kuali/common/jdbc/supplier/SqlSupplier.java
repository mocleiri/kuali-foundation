package org.kuali.common.jdbc.supplier;

public interface SqlSupplier {

	/**
	 * Return a single SQL statement to execute. Return <code>null</code> when the SQL supply has been exhausted.
	 */
	String getSql();

}
