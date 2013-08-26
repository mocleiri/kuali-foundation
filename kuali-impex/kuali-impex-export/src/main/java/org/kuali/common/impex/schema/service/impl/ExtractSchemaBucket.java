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

package org.kuali.common.impex.schema.service.impl;

import java.util.List;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.service.ExtractSchemaContext;
import org.kuali.common.util.inform.PercentCompleteInformer;

public class ExtractSchemaBucket {

	ExtractSchemaContext context;
	List<String> tableNames;
	Schema schema;
	PercentCompleteInformer informer;

	public ExtractSchemaContext getContext() {
		return context;
	}

	public void setContext(ExtractSchemaContext context) {
		this.context = context;
	}

	public List<String> getTableNames() {
		return tableNames;
	}

	public void setTableNames(List<String> tableNames) {
		this.tableNames = tableNames;
	}

	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	public PercentCompleteInformer getInformer() {
		return informer;
	}

	public void setInformer(PercentCompleteInformer informer) {
		this.informer = informer;
	}
}
