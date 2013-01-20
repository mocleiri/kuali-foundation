package org.kuali.common.jdbc.context;

import org.kuali.common.jdbc.JdbcService;
import org.kuali.common.jdbc.SqlBucket;

public class SqlBucketContext {

	SqlBucket bucket;
	ExecutionContext context;
	JdbcService service;

	public SqlBucket getBucket() {
		return bucket;
	}

	public void setBucket(SqlBucket bucket) {
		this.bucket = bucket;
	}

	public ExecutionContext getContext() {
		return context;
	}

	public void setContext(ExecutionContext context) {
		this.context = context;
	}

	public JdbcService getService() {
		return service;
	}

	public void setService(JdbcService service) {
		this.service = service;
	}

}
