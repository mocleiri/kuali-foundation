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
import java.util.Collection;

import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.schema.service.SchemaExtractionContext;
import org.kuali.common.impex.schema.service.SchemaExtractionService;
import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;

public class ExtractSchemaBucketHandler implements ElementHandler<ExtractSchemaBucket> {

	SchemaExtractionService service;

	public ExtractSchemaBucketHandler(SchemaExtractionService service) {
		this.service = service;
	}

	@Override
	public void handleElement(ListIteratorContext<ExtractSchemaBucket> context, int index, ExtractSchemaBucket element) {
		Schema schema = element.getSchema();
		SchemaExtractionContext extractionContext = element.getContext();

		if (element instanceof ExtractViewsAndSequencesBucket) {
			Collection<View> views;
			Collection<Sequence> sequences;

			try {
				views = service.extractViews(extractionContext);
			} catch (SQLException e) {
				throw new RuntimeException("Exception thrown by extraction service attempting to extract view metadata: " + e.getMessage(), e);
			}

			try {
				sequences = service.extractSequences(extractionContext);
			} catch (SQLException e) {
				throw new RuntimeException("Exception thrown by extraction service attempting to extract sequence metadata: " + e.getMessage(), e);
			}

			schema.getViews().addAll(views);
			schema.getSequences().addAll(sequences);
		} else {

			Collection<Table> tables;

			Collection<ForeignKey> foreignKeys;

			try {
				tables = service.extractTables(element.getTableNames(), extractionContext);
			} catch (SQLException e) {
				throw new RuntimeException("Exception thrown by extraction service attempting to extract table metadata: " + e.getMessage(), e);
			}

			try {
				foreignKeys = service.extractForeignKeys(element.getTableNames(), extractionContext);
			} catch (SQLException e) {
				throw new RuntimeException("Exception thrown by extraction service attempting to extract foreign key metadata: " + e.getMessage(), e);
			}

			schema.getTables().addAll(tables);
			schema.getForeignKeys().addAll(foreignKeys);
		}
	}
}
