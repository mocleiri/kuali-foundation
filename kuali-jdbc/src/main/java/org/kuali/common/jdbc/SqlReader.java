package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;

public interface SqlReader {

	String readSql(BufferedReader reader) throws IOException;

}
