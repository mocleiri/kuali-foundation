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
import org.kuali.common.util.execute.Executable;

public class SchemaExtractionExecutable implements Executable {

	public static final boolean DEFAULT_SKIP = false;

	SchemaExtractionService service;
	SchemaExtractionContext context;
	SchemaExtractionResult result;

	boolean skip = DEFAULT_SKIP;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		Schema schema = service.getSchema(context);
		result.setSchema(schema);
	}

	public SchemaExtractionContext getContext() {
		return context;
	}

	public void setContext(SchemaExtractionContext context) {
		this.context = context;
	}

	public SchemaExtractionService getService() {
		return service;
	}

	public void setService(SchemaExtractionService service) {
		this.service = service;
	}

	public SchemaExtractionResult getResult() {
		return result;
	}

	public void setResult(SchemaExtractionResult result) {
		this.result = result;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
}
