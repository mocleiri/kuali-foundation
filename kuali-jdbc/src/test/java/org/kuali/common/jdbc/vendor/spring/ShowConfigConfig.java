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
package org.kuali.common.jdbc.vendor.spring;

import org.kuali.common.jdbc.model.context.DatabaseProcessContext;
import org.kuali.common.jdbc.service.spring.DatabaseProcessContextConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.PrintMessageExecutable;
import org.kuali.common.util.spring.config.annotation.Execute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DatabaseProcessContextConfig.class)
public class ShowConfigConfig {

	@Autowired
	DatabaseProcessContext context;

	@Execute
	protected Executable executable() {
		PrintMessageExecutable exec = new PrintMessageExecutable();
		String message = context.getVendor();
		exec.setMessage(message);
		return exec;
	}
}
