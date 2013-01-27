package org.kuali.common.jdbc;

/**
 *
 */
public class MySQLDumpReader extends DefaultSqlReader {

	public MySQLDumpReader() {
		super();
		setDelimiter(";");
		setDelimiterMode(DelimiterMode.END_OF_LINE);
	}

}
