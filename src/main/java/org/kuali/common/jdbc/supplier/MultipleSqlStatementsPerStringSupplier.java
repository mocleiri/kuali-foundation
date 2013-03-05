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

public class MultipleSqlStatementsPerStringSupplier implements SqlSupplier {

	protected int index = 0;

	List<String> strings;
	SqlReader reader;

	@Override
	public void open() {

		// Make sure we've got something to work with
		Assert.notNull(strings, "strings is null");

		// Reset index to zero
		index = 0;
	}

	@Override
	public String getSql() {
		if (index < strings.size()) {
			return strings.get(index++);
		} else {
			return null;
		}
	}

	@Override
	public void close() {
		// Reset index to zero
		index = 0;
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
