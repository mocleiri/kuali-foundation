/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.jdbc.model.context;

import org.kuali.common.jdbc.model.SqlBucket;
import org.kuali.common.jdbc.service.JdbcService;
import org.kuali.common.util.Assert;

public final class SqlBucketContext {

	public SqlBucketContext(SqlBucket bucket, JdbcContext context, JdbcService service) {
		Assert.noNulls(bucket, context, service);
		this.bucket = bucket;
		this.context = context;
		this.service = service;
	}

	private final SqlBucket bucket;
	private final JdbcContext context;
	private final JdbcService service;

	public SqlBucket getBucket() {
		return bucket;
	}

	public JdbcContext getContext() {
		return context;
	}

	public JdbcService getService() {
		return service;
	}

}
