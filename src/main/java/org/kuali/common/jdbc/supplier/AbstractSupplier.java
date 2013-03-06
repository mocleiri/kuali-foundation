package org.kuali.common.jdbc.supplier;

import org.kuali.common.jdbc.SqlMetaData;

public abstract class AbstractSupplier implements SqlSupplier {

	protected SqlMetaData metaData;

	@Override
	public SqlMetaData getMetaData() {
		return metaData;
	}

	@Override
	public int compareTo(SqlSupplier other) {
		Long one = metaData.getSize();
		Long two = other.getMetaData().getSize();
		return one.compareTo(two);
	}

}
