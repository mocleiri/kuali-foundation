package org.kuali.common.jdbc.supplier;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.util.LocationUtils;
import org.springframework.util.Assert;

/**
 * Supply SQL from a List of strings containing SQL statements, where each String can have more than one SQL statement
 */
public class ComplexStringSupplier implements SqlSupplier {

	protected int index = 0;
	protected BufferedReader in;

	List<String> strings;
	SqlReader reader;

	@Override
	public void open() {

		// Make sure we've got something to work with
		Assert.notNull(strings, "strings is null");

		// Reset index to zero
		index = 0;

		// Open a reader to the first string in the list
		in = getBufferedReader(strings, index);
	}

	@Override
	public String getSql() {
		try {
			// Have the reader produce a SQL statement
			String sql = reader.getSqlStatement(in);

			// We got a SQL statement we are done
			if (sql != null) {
				return sql;
			}

			// We've exhausted the current string, move to the next one
			index++;

			// We've exhausted all of the strings, we are done
			if (index == strings.size()) {
				return null;
			}

			// Open a reader to the new string
			in = getBufferedReader(strings, index);

			// Get a SQL statement from the new string
			return getSql();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Extract a String from the list and open a BufferedReader that can read from it
	 */
	protected BufferedReader getBufferedReader(List<String> strings, int index) {
		String string = strings.get(index);
		return LocationUtils.getBufferedReaderFromString(string);
	}

	@Override
	public void close() {
		// Reset index to zero
		index = 0;
		IOUtils.closeQuietly(in);
	}

	@Override
	public SqlMetaData getSqlMetaData() {
		long count = 0;
		long size = 0;
		for (String string : strings) {
			SqlMetaData smd = getSqlMetaData(string);
			count += smd.getCount();
			size += smd.getSize();
		}
		return new SqlMetaData(count, size);
	}

	protected SqlMetaData getSqlMetaData(String sql) {
		BufferedReader in = null;
		try {
			in = LocationUtils.getBufferedReaderFromString(sql);
			return JdbcUtils.getSqlMetaData(in, reader);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

}
