package org.kuali.common.jdbc.supplier;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.util.LocationUtils;
import org.springframework.util.Assert;

public class LocationSupplier implements SqlSupplier {

	private BufferedReader in;

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
			SqlMetaData smd = new SqlMetaData();
			smd.setCount(count);
			smd.setSize(size);
			return smd;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}

	}
}
