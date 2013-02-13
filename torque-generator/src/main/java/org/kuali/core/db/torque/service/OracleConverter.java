package org.kuali.core.db.torque.service;

import java.util.List;

import org.apache.torque.engine.database.model.Column;

public class OracleConverter implements SqlConverter {

	@Override
	public List<String> getSqlValues(List<Column> columns, String[] tokens) {
		throw new IllegalStateException("Not implemented");
	}
}
