package org.kuali.common.jdbc;

import java.io.BufferedReader;

public interface SqlReader {

	BufferedReader getSqlReader(String location);

	BufferedReader getSqlReaderFromString(String sql);

	String getSqlStatement(BufferedReader reader);

}
