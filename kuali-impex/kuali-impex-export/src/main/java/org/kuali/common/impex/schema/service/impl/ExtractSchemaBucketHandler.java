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

import java.sql.SQLException;
import java.util.List;

import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.schema.service.ExtractSchemaContext;
import org.kuali.common.impex.schema.service.ExtractSchemaService;
import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;

public class ExtractSchemaBucketHandler implements ElementHandler<ExtractSchemaBucket> {

	public static final ExtractSchemaService DEFAULT_SERVICE = new DefaultExtractSchemaService();

	ExtractSchemaService service = DEFAULT_SERVICE;

	public ExtractSchemaBucketHandler() {
		this(DEFAULT_SERVICE);
	}

	public ExtractSchemaBucketHandler(ExtractSchemaService service) {
		this.service = service;
	}

	@Override
	public void handleElement(ListIteratorContext<ExtractSchemaBucket> context, int index, ExtractSchemaBucket element) {
		// TODO This instanceof check is pretty awful. Do something smarter here
		if (element instanceof ExtractViewsAndSequencesBucket) {
			doViewsAndSequences(element, element.getContext(), element.getSchema());
		} else {
			doTablesAndForeignKeys(element, element.getContext(), element.getSchema());
		}
	}

	protected void doTablesAndForeignKeys(ExtractSchemaBucket bucket, ExtractSchemaContext context, Schema schema) {
		try {
			List<Table> tables = service.extractTables(bucket.getTableNames(), context);
			List<ForeignKey> foreignKeys = service.extractForeignKeys(bucket.getTableNames(), context);
			synchronized (schema) {
				schema.getTables().addAll(tables);
				schema.getForeignKeys().addAll(foreignKeys);
			}
		} catch (SQLException e) {
			throw new IllegalStateException("Unexpected SQL error");
		}

	}

	protected void doViewsAndSequences(ExtractSchemaBucket bucket, ExtractSchemaContext context, Schema schema) {
		try {
			List<View> views = service.extractViews(context);
			bucket.getInformer().incrementProgress();

			List<Sequence> sequences = service.extractSequences(context);
			bucket.getInformer().incrementProgress();

			synchronized (schema) {
				schema.getViews().addAll(views);
				schema.getSequences().addAll(sequences);
			}
		} catch (SQLException e) {
			throw new IllegalStateException("Unexpected SQL error");
		}
	}
}
