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
package org.kuali.common.util.metainf.spring;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.PrintMessageExecutable;
import org.kuali.common.util.spring.ExecutableConfig;
import org.kuali.common.util.spring.config.annotation.Default;
import org.kuali.common.util.spring.config.annotation.Maven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfig extends ExecutableConfig {

	private static final String VENDOR_KEY = "databaseVendor";
	private static final String DRIVER_KEY = "jdbcDriver";

	@Autowired
	@Qualifier(VENDOR_KEY)
	String vendor;

	@Autowired
	@Qualifier(DRIVER_KEY)
	String driver;

	@Override
	protected Executable getExecutable() {
		PrintMessageExecutable exec = new PrintMessageExecutable();
		exec.setMessage(vendor + ":" + driver);
		return exec;
	}

	@Configuration
	@Default
	static class a {

		@Autowired
		@Qualifier(DRIVER_KEY)
		String driver;

		@Bean(name = VENDOR_KEY)
		public String msg() {
			return "oracle";
		}
	}

	@Configuration
	@Maven
	static class b {

		@Bean(name = DRIVER_KEY)
		public String driver() {
			return "com.mysql.Driver";
		}

		@Bean(name = VENDOR_KEY)
		public String msg() {
			return "mysql";
		}
	}

}
