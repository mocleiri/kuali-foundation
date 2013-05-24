package org.kuali.common.jalc.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.kuali.common.jalc.model.Table;

public interface SqlProducer {

	List<String> getSql(Table table, MpxHeaderData headerData, BufferedReader reader) throws IOException;

	int getBatchDataSizeLimit();

	int getBatchRowCountLimit();

	void setBatchDataSizeLimit(int batchDataSizeLimit);

	void setBatchRowCountLimit(int batchRowCountLimit);
}
