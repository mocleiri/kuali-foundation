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

import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.util.Assert;

public final class DatabaseProcessContext {

	public DatabaseProcessContext(DatabaseVendor vendor, ConnectionContext connectionContext, String encoding, String schema) {
		Assert.noNulls(vendor, connectionContext);
		Assert.noBlanks(encoding, schema);
		this.vendor = vendor;
		this.connectionContext = connectionContext;
		this.encoding = encoding;
		this.schema = schema;
	}

	private final DatabaseVendor vendor;
	private final ConnectionContext connectionContext;
	private final String encoding;
	private final String schema;

	public DatabaseVendor getVendor() {
		return vendor;
	}

	public String getEncoding() {
		return encoding;
	}

	public String getSchema() {
		return schema;
	}

	public ConnectionContext getConnectionContext() {
		return connectionContext;
	}

}
