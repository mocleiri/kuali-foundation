package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;

public interface SqlReader {

	BufferedReader getSqlReader(String location);

	BufferedReader getSqlReaderFromString(String sql);

	String getSqlStatement(BufferedReader reader) throws IOException;

}
