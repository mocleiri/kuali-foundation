package org.kuali.common.jdbc.supplier;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.util.LocationUtils;
import org.springframework.util.Assert;

public class LocationSupplier implements SqlSupplier {

	protected BufferedReader in;

	String location;
	String encoding;
	SqlReader reader;

	@Override
	public void open() throws IOException {
		Assert.notNull(location, "location is null");
		Assert.notNull(reader, "reader is null");
		in = LocationUtils.getBufferedReader(location, encoding);
	}

	@Override
	public String getSql() throws IOException {
		return reader.getSqlStatement(in);
	}

	@Override
	public void close() {
		IOUtils.closeQuietly(in);
	}

	@Override
	public SqlMetaData getSqlMetaData() {
		long count = 0;
		long size = 0;

		BufferedReader in = null;
		try {
			in = LocationUtils.getBufferedReader(location, encoding);
			String sql = reader.getSqlStatement(in);
			while (sql != null) {
				count++;
				size += sql.length();
				sql = reader.getSqlStatement(in);
			}
			return new SqlMetaData(count, size);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}

	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public SqlReader getReader() {
		return reader;
	}

	public void setReader(SqlReader reader) {
		this.reader = reader;
	}
}
