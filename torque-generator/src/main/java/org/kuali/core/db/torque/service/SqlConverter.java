package org.kuali.core.db.torque.service;

import java.util.List;

import org.apache.torque.engine.database.model.Column;

public interface SqlConverter {

	/**
	 * Return a value usable in an SQL insert statement
	 */
	List<String> getSqlValues(Column[] columns, String[] tokens);

}
