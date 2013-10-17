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
package org.kuali.common.jdbc.spring;

import org.kuali.common.util.execute.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Default SQL controller class. It displays the JDBC configuration, then executes SQL statements organized into groups. Use <code>SqlControllerExectuableConfig</code> instead.
 * 
 * @deprecated
 */
@Configuration
@Import(BaseSqlControllerConfig.class)
@Deprecated
public class SqlControllerConfig extends org.kuali.common.util.spring.ExecutableConfig {

	@Autowired
	BaseSqlControllerConfig abstractSqlController;

	@Override
	protected Executable getExecutable() {
		return abstractSqlController.sqlExecutable();
	}

}
