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
import org.kuali.common.impex.schema.service.impl.DefaultSchemaDumpService;
import org.kuali.common.util.execute.Executable;

public class SchemaDumpExecutable implements Executable {

	public static final boolean DEFAULT_SKIP = false;
	public static final SchemaDumpService DEFAULT_SERVICE = new DefaultSchemaDumpService();

	boolean skip = DEFAULT_SKIP;
	SchemaDumpService service = DEFAULT_SERVICE;

	SchemaDumpResult result;
	SchemaDumpContext context;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		Schema schema = service.getSchema(context);
		this.result = new SchemaDumpResult();
		result.setSchema(schema);
	}

	/**
	 * Expose <code>SchemaExtractionResult</code> via a getter
	 */
	public SchemaDumpResult getResult() {
		return result;
	}

	public SchemaDumpContext getContext() {
		return context;
	}

	public void setContext(SchemaDumpContext context) {
		this.context = context;
	}

	public SchemaDumpService getService() {
		return service;
	}

	public void setService(SchemaDumpService service) {
		this.service = service;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
}
