package org.kuali.common.jdbc;

import java.util.List;

public class SqlMetadata {

	long count;

	List<SqlSourceMetadata> sourceMetadata;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<SqlSourceMetadata> getSourceMetadata() {
		return sourceMetadata;
	}

	public void setSourceMetadata(List<SqlSourceMetadata> sourceMetadata) {
		this.sourceMetadata = sourceMetadata;
	}

}
