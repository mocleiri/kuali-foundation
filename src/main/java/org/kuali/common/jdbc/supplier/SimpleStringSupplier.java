package org.kuali.common.jdbc.supplier;

import java.util.List;

import org.kuali.common.jdbc.SqlMetaData;
import org.springframework.util.Assert;

/**
 * Supply SQL from a List of strings containing SQL statements
 */
public class SimpleStringSupplier implements SqlSupplier {

	protected int index = 0;

	List<String> strings;

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
		int count = strings.size();
		long size = 0;
		for (String string : strings) {
			size += string.length();
		}
		return new SqlMetaData(count, size);
	}

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

}
