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
		return Double.compare(metaData.getSize(), other.getMetaData().getSize());
	}

}
