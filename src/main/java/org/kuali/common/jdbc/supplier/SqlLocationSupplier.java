package org.kuali.common.jdbc.supplier;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.kuali.common.jdbc.DefaultSqlReader;
import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.util.LocationUtils;
import org.springframework.util.Assert;

/**
 * Supply SQL from a location containing pre-generated SQL statements
 */
public class SqlLocationSupplier extends AbstractSupplier {

	protected BufferedReader in;

	String location;
	String encoding = "UTF-8";
	SqlReader reader = new DefaultSqlReader();

	public SqlLocationSupplier() {
		this(null);
	}

	public SqlLocationSupplier(String location) {
		super();
		this.location = location;
	}

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
	public void fillInMetaData() {
		BufferedReader in = null;
		try {
			in = LocationUtils.getBufferedReader(location, encoding);
			this.metaData = JdbcUtils.getSqlMetaData(in, reader);
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
