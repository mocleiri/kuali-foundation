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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	@Autowired
	@Qualifier("foo")
	String vendor;

	@Bean
	public Database databaseConfigDatabase() {
		DefaultDatabase dd = new DefaultDatabase();
		dd.setVendor(vendor);
		return dd;
	}

	@Configuration
	@Oracle
	static class b {
		@Bean(name = "foo")
		public String blibbity() {
			return "oracle";
		}
	}

	@Configuration
	static class a {
		@Bean(name = "foo")
		public String blabbity() {
			return "mysql";
		}
	}

}
