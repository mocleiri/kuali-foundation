package org.kuali.common.jdbc;

import java.io.BufferedReader;

public interface SqlReader {

	BufferedReader getBufferedReader(String location);

	BufferedReader getBufferedStringReader(String sql);

	String getSqlStatement(BufferedReader reader);

}
