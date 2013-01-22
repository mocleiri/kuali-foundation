package org.kuali.common.jdbc.listener;

import java.util.List;

import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.jdbc.threads.SqlBucket;

public class BucketEvent {

	ExecutionContext context;
	List<SqlBucket> buckets;

	public BucketEvent() {
		this(null, null);
	}

	public BucketEvent(ExecutionContext context, List<SqlBucket> buckets) {
		super();
		this.context = context;
		this.buckets = buckets;
	}

	public ExecutionContext getContext() {
		return context;
	}

	public void setContext(ExecutionContext context) {
		this.context = context;
	}

	public List<SqlBucket> getBuckets() {
		return buckets;
	}

	public void setBuckets(List<SqlBucket> buckets) {
		this.buckets = buckets;
	}

}
