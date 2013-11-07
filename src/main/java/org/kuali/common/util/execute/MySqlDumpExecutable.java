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
package org.kuali.common.util.execute;

import org.kuali.common.util.service.MySqlDumpContext;
import org.kuali.common.util.service.MySqlDumpService;

public class MySqlDumpExecutable implements Executable {

	MySqlDumpService service;
	MySqlDumpContext context;

	@Override
	public void execute() {
		service.dump(context);
	}

	public MySqlDumpService getService() {
		return service;
	}

	public void setService(MySqlDumpService service) {
		this.service = service;
	}

	public MySqlDumpContext getContext() {
		return context;
	}

	public void setContext(MySqlDumpContext context) {
		this.context = context;
	}

}
