package org.kuali.common.impex.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.apache.torque.engine.database.model.Table;

public interface SqlProducer {

	List<String> getSql(Table table, BufferedReader reader) throws IOException;

	int getBatchDataSizeLimit();

	int getBatchRowCountLimit();

	void setBatchDataSizeLimit(int batchDataSizeLimit);

	void setBatchRowCountLimit(int batchRowCountLimit);
}
