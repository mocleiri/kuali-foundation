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
package org.kuali.common.util.spring.profile;

import org.kuali.common.util.spring.config.annotation.Default;
import org.kuali.common.util.spring.profile.annotation.MySql;
import org.kuali.common.util.spring.profile.annotation.Oracle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	private static final String VENDOR_BEAN = DatabaseConstants.VENDOR_BEAN_NAME;

	@Autowired
	@Qualifier(VENDOR_BEAN)
	String vendor;

	@Bean
	public Database databaseConfigDatabase() {
		DefaultDatabase dd = new DefaultDatabase();
		dd.setVendor(vendor);
		return dd;
	}

	@Configuration
	@MySql
	static class a {

		@Autowired
		@Qualifier("foo")
		String bar;

		@Bean(name = VENDOR_BEAN)
		public String aa() {
			return "mysql";
		}
	}

	@Configuration
	@Oracle
	static class b {

		@Autowired
		@Qualifier("foo")
		String bar;

		@Bean(name = VENDOR_BEAN)
		public String aa() {
			return "oracle";
		}
	}

	@Configuration
	@Default
	static class c {
		@Bean(name = VENDOR_BEAN)
		public String aa() {
			return "h2";
		}
	}

}
