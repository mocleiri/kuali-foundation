package org.kuali.common.jdbc.threads;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.SqlSource;

public class SqlBucket implements Comparable<SqlBucket> {

	long count;
	long size;
	List<SqlSource> sources = new ArrayList<SqlSource>();

	@Override
	public int compareTo(SqlBucket other) {
		Long size1 = this.size;
		Long size2 = other.getSize();
		return size1.compareTo(size2);
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public List<SqlSource> getSources() {
		return sources;
	}

	public void setSources(List<SqlSource> sources) {
		this.sources = sources;
	}

}
