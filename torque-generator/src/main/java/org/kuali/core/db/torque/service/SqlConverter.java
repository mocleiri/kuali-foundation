package org.kuali.core.db.torque.service;

import org.apache.torque.engine.database.model.Column;

public interface SqlConverter {

	/**
	 * Return a value usable in an SQL insert statement
	 */
	String getSqlValue(Column column, String token);

}
