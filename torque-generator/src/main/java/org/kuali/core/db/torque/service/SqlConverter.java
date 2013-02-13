package org.kuali.core.db.torque.service;

import org.apache.torque.engine.database.model.Column;

public interface SqlConverter {

	String getSqlValue(Column column, String token);

}
