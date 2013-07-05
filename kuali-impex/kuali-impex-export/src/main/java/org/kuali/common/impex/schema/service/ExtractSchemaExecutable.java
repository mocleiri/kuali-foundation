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

package org.kuali.common.impex.schema.service;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.service.impl.DefaultExtractSchemaService;
import org.kuali.common.util.execute.Executable;
import org.springframework.util.Assert;

public class ExtractSchemaExecutable implements Executable {

	public static final boolean DEFAULT_SKIP = false;
	public static final ExtractSchemaService DEFAULT_SERVICE = new DefaultExtractSchemaService();

	boolean skip = DEFAULT_SKIP;
	ExtractSchemaService service = DEFAULT_SERVICE;

	// Required
	ExtractSchemaContext context;

	// Filled in during execution
	ExtractSchemaResult result;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		Assert.notNull(service, "service is null");
		Assert.notNull(context, "context is null");

		Schema schema = service.getSchema(context);
		this.result = new ExtractSchemaResult(schema);
	}

	/**
	 * Expose <code>SchemaExtractionResult</code> via a getter
	 */
	public ExtractSchemaResult getResult() {
		return result;
	}

	public ExtractSchemaContext getContext() {
		return context;
	}

	public void setContext(ExtractSchemaContext context) {
		this.context = context;
	}

	public ExtractSchemaService getService() {
		return service;
	}

	public void setService(ExtractSchemaService service) {
		this.service = service;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
}
