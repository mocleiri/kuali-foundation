package org.kuali.common.jdbc.supplier;

import java.util.List;

import org.kuali.common.jdbc.SqlMetaData;
import org.springframework.util.Assert;

/**
 * Supply SQL from strings that have one SQL statement each
 */
public class SimpleStringSupplier extends AbstractSupplier {

	List<String> strings;

	public SimpleStringSupplier() {
		this(null);
	}

	public SimpleStringSupplier(List<String> strings) {
		super();
		this.strings = strings;
	}

	@Override
	public void open() {
		// Make sure we've got something to work with
		Assert.notNull(strings, "strings is null");
	}

	@Override
	public List<String> getSql() {
		return strings;
	}

	@Override
	public void close() {
		// Nothing to do
	}

	@Override
	public void fillInMetaData() {
		int count = strings.size();
		long size = 0;
		for (String string : strings) {
			size += string.length();
		}
		this.metaData = new SqlMetaData(count, size);
	}

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

}
