package org.kuali.common.jdbc;

import java.io.BufferedReader;

public interface SqlReader {

	BufferedReader getBufferedReader(String location);

	String getSqlStatement(BufferedReader reader);

}
