package org.kuali.core.db.torque.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.apache.torque.engine.database.model.Table;

public interface ImpexReader {

	public String getInsertSql(Table table, BufferedReader reader, ImpexContext context) throws IOException;

}
