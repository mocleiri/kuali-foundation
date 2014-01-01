/**
 * Copyright 2010-2014 The Kuali Foundation
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
