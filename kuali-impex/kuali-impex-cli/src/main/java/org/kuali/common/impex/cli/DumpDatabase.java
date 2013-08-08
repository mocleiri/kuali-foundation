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

package org.kuali.common.impex.cli;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.PrintMessageExecutable;
import org.kuali.common.util.spring.config.annotation.Execute;
import org.kuali.common.util.spring.main.MainContext;
import org.kuali.common.util.spring.main.MainUtils;
import org.kuali.common.util.spring.service.PropertySourceService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class DumpDatabase {

	public static void main(String[] args) {
		MainUtils.runAndExit(DumpDatabase.class, args);
	}

	@Autowired
	MainContext context;

	@Autowired
	PropertySourceService propertySourceService;

	@Execute
	public Executable executable() {
		String[] args = context.getArgs();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			sb.append("args[" + i + "]=" + args[i] + "\n");
		}
		return new PrintMessageExecutable(sb.toString());
	}

}
