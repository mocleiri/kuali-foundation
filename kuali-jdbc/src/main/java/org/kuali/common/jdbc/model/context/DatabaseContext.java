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

import java.sql.Driver;

import org.kuali.common.jdbc.model.Vendors;
import org.kuali.common.util.Assert;

public final class DatabaseContext {

	public DatabaseContext(Vendors vendor, Class<? extends Driver> driver, ConnectionContext context, String encoding, String schema) {
		this(vendor, driver, context, context, encoding, schema);
	}

	public DatabaseContext(Vendors vendor, Class<? extends Driver> driver, ConnectionContext normal, ConnectionContext dba, String encoding, String schema) {
		Assert.noNulls(vendor, driver, normal, dba);
		Assert.noBlanks(encoding, schema);
		this.vendor = vendor;
		this.driver = driver;
		this.normal = normal;
		this.dba = dba;
		this.encoding = encoding;
		this.schema = schema;
	}

	private final Vendors vendor;
	private final Class<? extends Driver> driver;
	private final ConnectionContext normal;
	private final ConnectionContext dba;
	private final String encoding;
	private final String schema;

	public Vendors getVendor() {
		return vendor;
	}

	public Class<? extends Driver> getDriver() {
		return driver;
	}

	public ConnectionContext getNormal() {
		return normal;
	}

	public ConnectionContext getDba() {
		return dba;
	}

	public String getEncoding() {
		return encoding;
	}

	public String getSchema() {
		return schema;
	}

}
