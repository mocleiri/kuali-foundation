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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Default database reset controller class. It displays the JDBC configuration, then executes a series of SQL statements in order [dba->schema->data->constraints->other].
 */
@Configuration
public class SqlControllerConfig extends AbstractSqlController {

	@Bean(initMethod = "execute")
	public Executable sqlExecutable() {
		return getSqlExecutable();
	}

}
