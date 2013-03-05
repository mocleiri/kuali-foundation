package org.kuali.common.jdbc.supplier;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.util.LocationUtils;
import org.springframework.util.Assert;

public class LocationSupplier implements SqlSupplier {

	String location;
	String encoding;
	SqlReader reader;

	BufferedReader in;
	boolean done = false;

	@Override
	public synchronized String getSql() {

		Assert.notNull(location, "location is null");
		Assert.notNull(reader, "reader is null");

		if (done) {
			return null;
		}

		try {
			if (in == null) {
				in = LocationUtils.getBufferedReader(location, encoding);
			}
			String sql = reader.getSqlStatement(in);
			if (sql == null) {
				done = true;
				IOUtils.closeQuietly(in);
			}
			return sql;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

}
