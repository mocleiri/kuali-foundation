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
	private boolean done = false;
	private boolean initialized = false;

	String location;
	String encoding;
	SqlReader reader;

	@Override
	public synchronized String getSql() {

		Assert.notNull(location, "location is null");
		Assert.notNull(reader, "reader is null");

		if (done) {
			return null;
		}

		try {
			init();
			String sql = reader.getSqlStatement(in);
			if (sql == null) {
				done = true;
				IOUtils.closeQuietly(in);
			}
			return sql;
		} catch (IOException e) {
			done = true;
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
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

	protected void init() throws IOException {
		if (!initialized) {
			in = LocationUtils.getBufferedReader(location, encoding);
			initialized = true;
		}
	}

}
