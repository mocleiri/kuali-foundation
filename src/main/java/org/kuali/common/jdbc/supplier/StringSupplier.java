package org.kuali.common.jdbc.supplier;

import java.util.List;

import org.kuali.common.jdbc.SqlMetaData;
import org.springframework.util.Assert;

public class StringSupplier implements SqlSupplier {

	protected int index = 0;

	List<String> strings;

	@Override
	public void open() {

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
		// nothing to do
	}

	@Override
	public SqlMetaData getSqlMetaData() {
		int count = strings.size();

		long size = 0;
		for (String string : strings) {
			size += string.length();
		}

		SqlMetaData smd = new SqlMetaData();
		smd.setCount(count);
		smd.setSize(size);
		return smd;
	}

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

}
