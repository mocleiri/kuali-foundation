package org.kuali.common.jdbc.supplier;

import java.util.List;

import org.kuali.common.jdbc.SqlMetaData;
import org.springframework.util.Assert;

public class StringSupplier implements SqlSupplier {

	private int index = 0;

	List<String> strings;

	@Override
	public synchronized String getSql() {

		Assert.notNull(strings, "strings is null");

		if (index < strings.size()) {
			return strings.get(index++);
		} else {
			return null;
		}
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
