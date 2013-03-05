package org.kuali.common.jdbc.supplier;

import org.kuali.common.jdbc.SqlMetaData;

public abstract class AbstractSupplier implements Comparable<SqlSupplier> {

	protected SqlMetaData metaData;

	public SqlMetaData getMetaData() {
		return metaData;
	}

	@Override
	public int compareTo(SqlSupplier other) {
		Long one = metaData.getCount();
		Long two = other.getMetaData().getCount();
		return one.compareTo(two);
	}

}
