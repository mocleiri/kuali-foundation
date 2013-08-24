/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.cli.dump;

import org.kuali.common.impex.spring.DumpDatabaseExecutableConfig;
import org.kuali.common.util.main.MainUtils;
import org.kuali.common.util.main.spring.AbstractMainRunner;
import org.kuali.common.util.main.spring.MainServiceConfig;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, MainServiceConfig.class })
public class DumpDatabase extends AbstractMainRunner {

	public static void main(String[] args) {
		MainUtils.runAndExit(DumpDatabase.class, args);
	}

	@Override
	protected Class<? extends PropertySourceConfig> getPropertySourceConfig() {
		return DumpDatabasePropertySourceConfig.class;
	}

	@Override
	protected Class<?> getConfig() {
		return DumpDatabaseExecutableConfig.class;
	}

}
