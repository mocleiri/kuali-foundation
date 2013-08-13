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
package org.kuali.common.jdbc.model.meta;

import org.kuali.common.util.Assert;

public final class JdbcMetaData {

	private JdbcMetaData(Product product, Driver driver, String url, String username) {
		Assert.noNulls(product, driver);
		Assert.noBlanks(url, username);
		this.product = product;
		this.driver = driver;
		this.url = url;
		this.username = username;
	}

	private final Product product;
	private final Driver driver;
	private final String url;
	private final String username;

	public Product getProduct() {
		return product;
	}

	public Driver getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

}
