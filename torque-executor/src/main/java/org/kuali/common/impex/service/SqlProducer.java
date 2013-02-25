package org.kuali.common.impex.service;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.torque.engine.database.model.Table;

public interface SqlProducer {

	public String getSql(Table table, BufferedReader reader) throws IOException;

    void setBatchDataSizeLimit(int batchDataSizeLimit);

    void setBatchRowCountLimit(int batchRowCountLimit);
}
