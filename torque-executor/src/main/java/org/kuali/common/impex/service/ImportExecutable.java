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

package org.kuali.common.impex.service;

import java.io.IOException;

import org.kuali.common.impex.ImportContext;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @author andrewlubbers
 */
public class ImportExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(ImportExecutable.class);

	ImpexExecutorService service;
	ImportContext context;
	JdbcContext sqlExecutionContext;

	@Override
	public void execute() {
		Assert.notNull(context);
		Assert.notNull(service);
		Assert.notNull(sqlExecutionContext);

		logger.info("Starting Import");

		// import the data from the generated mpx files
		try {
			service.importData(context, sqlExecutionContext);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		logger.info("Import Complete");
	}

	public ImpexExecutorService getService() {
		return service;
	}

	public void setService(ImpexExecutorService service) {
		this.service = service;
	}

	public ImportContext getContext() {
		return context;
	}

	public void setContext(ImportContext context) {
		this.context = context;
	}

	public JdbcContext getSqlExecutionContext() {
		return sqlExecutionContext;
	}

	public void setSqlExecutionContext(JdbcContext sqlExecutionContext) {
		this.sqlExecutionContext = sqlExecutionContext;
	}
}
