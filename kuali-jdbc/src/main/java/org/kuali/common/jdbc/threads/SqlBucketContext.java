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
package org.kuali.common.jdbc.threads;

import org.kuali.common.jdbc.JdbcService;
import org.kuali.common.jdbc.context.JdbcContext;

/**
 * @deprecated
 */
@Deprecated
public class SqlBucketContext {

	SqlBucket bucket;
	JdbcContext context;
	JdbcService service;

	public SqlBucket getBucket() {
		return bucket;
	}

	public void setBucket(SqlBucket bucket) {
		this.bucket = bucket;
	}

	public JdbcContext getContext() {
		return context;
	}

	public void setContext(JdbcContext context) {
		this.context = context;
	}

	public JdbcService getService() {
		return service;
	}

	public void setService(JdbcService service) {
		this.service = service;
	}

}
