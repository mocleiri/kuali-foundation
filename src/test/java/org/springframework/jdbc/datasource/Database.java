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
package org.springframework.jdbc.datasource;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

@IdiotProofImmutable
public final class Database {

	private final String url;
	private final String username;
	private final Versioned product;
	private final Versioned driver;

	private Database(Builder builder) {
		this.url = builder.url;
		this.username = builder.username;
		this.product = builder.product;
		this.driver = builder.driver;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Database> {

		private String url;
		private String username;
		private Versioned product;
		private Versioned driver;

		public Builder withUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withProduct(Versioned product) {
			this.product = product;
			return this;
		}

		public Builder withDriver(Versioned driver) {
			this.driver = driver;
			return this;
		}

		@Override
		public Database build() {
			return validate(new Database(this));
		}
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public Versioned getProduct() {
		return product;
	}

	public Versioned getDriver() {
		return driver;
	}

}
