package org.kuali.core.db.torque.service;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.torque.engine.database.model.Table;

public class OracleImpexReader implements ImpexReader {

	@Override
	public String getInsertSql(Table table, BufferedReader reader) throws IOException {
		throw new IllegalStateException("Not implemented");
	}
}
