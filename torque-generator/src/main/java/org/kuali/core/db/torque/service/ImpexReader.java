package org.kuali.core.db.torque.service;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.torque.engine.database.model.Table;

public interface ImpexReader {

	String getInsertSql(Table table, BufferedReader reader) throws IOException;

}
