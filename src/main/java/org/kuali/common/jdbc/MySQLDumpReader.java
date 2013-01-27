package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 *
 * This works for SQL files generated by <code>mysqldump</code>. <code>mysqldump</code> files have the following characteristics that allow
 * this to work:<br>
 * 1 - Every individual SQL statement is on a single line. SQL containing carriage returns and linefeeds (html text for example) is escaped
 * and still contained on a single line<br>
 * 2 - Every individual SQL statement is ended with a semi-colon<br>
 *
 * Those 2 characteristics make parsing the SQL file a very trivial thing to do.
 */
public class MySQLDumpReader extends DefaultSqlReader {

	public MySQLDumpReader() {
		super();
		setDelimiter(";");
		setDelimiterMode(DelimiterMode.END_OF_LINE);
	}

	@Override
	public String getSqlStatement(BufferedReader reader) throws IOException {
		Assert.notNull(delimiter, "delimiter is null");
		String line = reader.readLine();
		String trimmedLine = StringUtils.trimToNull(line);

		while (line != null) {
			if (trimmedLine == null) {
				continue;
			}
			if (isSqlComment(trimmedLine, commentTokens)) {
				continue;
			}
			if (StringUtils.endsWith(line, delimiter)) {
				return line;
			}
			line = reader.readLine();
			trimmedLine = StringUtils.trimToNull(line);
		}
		return null;
	}
}
