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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.Index;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.UniqueConstraint;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.schema.service.SchemaExtractionContext;
import org.kuali.common.impex.schema.service.SchemaExtractionService;
import org.kuali.common.impex.util.ExtractionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.StringFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSchemaExtractionService implements SchemaExtractionService {

	private static Logger log = LoggerFactory.getLogger(DefaultSchemaExtractionService.class);

	protected static final int SINGLE_THREAD_COUNT = 1;

	@Override
	public Schema getSchema(SchemaExtractionContext context) {

		initializeContext(context);

		Schema result = null;

		try {
			if (context.getThreadCount() == SINGLE_THREAD_COUNT) {
				result = extractSingleThreaded(context);
			} else {
				// result = extractMultiThreaded(context);
			}

		} catch (SQLException e) {
			log.error("Exception building schema: " + e.getMessage(), e);
		}

		return result;
	}

	/**
	 * This method sets up the initial connection to the database and creates the DatabaseMetaData object
	 * 
	 * @param context
	 *            the Context to work with
	 */
	protected void initializeContext(SchemaExtractionContext context) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	protected Schema extractSingleThreaded(SchemaExtractionContext context) throws SQLException {
		long startTime = System.currentTimeMillis();
		log.info("Single threaded schema extraction started");

		Schema result = new Schema();

		List<String> tableNames = getTableNames(context);
		log.debug("Extracting {} tables...", new Object[] { tableNames.size() });
		result.getTables().addAll(extractTables(tableNames, context));
		log.debug("Table extraction complete.");

		result.getViews().addAll(extractViews(context));
		log.debug("View extraction complete");

		result.getSequences().addAll(extractSequences(context));
		log.debug("Sequence extraction complete");

		result.getForeignKeys().addAll(extractForeignKeys(tableNames, context));
		log.debug("Foreign Key extraction complete");

		String timeString = FormatUtils.getTime(System.currentTimeMillis() - startTime);
		log.info("Single threaded schema extraction complete - Time: {}", new Object[] { timeString });
		return result;

	}

	protected Collection<Table> extractTables(List<String> tableNames, SchemaExtractionContext context) throws SQLException {
		Collection<Table> results = new ArrayList<Table>(tableNames.size());

		for (String name : tableNames) {
			results.add(extractTable(name, context));
		}

		return results;
	}

	protected Table extractTable(String tablename, SchemaExtractionContext context) throws SQLException {
		Table result = new Table(tablename);

		result.setDescription(ExtractionUtils.extractTableComment(tablename, context.getSchemaName(), context.getDatabaseMetaData()));
		result.setColumns(ExtractionUtils.extractTableColumns(tablename, context.getSchemaName(), context.getDatabaseMetaData()));

		List<Index> allTableIndices = ExtractionUtils.extractTableIndices(tablename, context.getSchemaName(), context.getDatabaseMetaData());

		for (Index index : allTableIndices) {
			if (index.isUnique()) {
				UniqueConstraint u = new UniqueConstraint(index.getColumnNames(), index.getName());
				result.getUniqueConstraints().add(u);
			} else {
				result.getIndices().add(index);
			}
		}

		return result;
	}

	protected List<String> getTableNames(SchemaExtractionContext context) throws SQLException {
		List<String> allTables = ExtractionUtils.getTableNamesFromMetaData(context.getSchemaName(), context.getDatabaseMetaData());

		StringFilter nameFilter = StringFilter.getInstance(context.getElementNameIncludes(), context.getElementNameExcludes());

		List<String> filteredNames = new ArrayList<String>();
		for (String name : allTables) {
			if (nameFilter.include(name)) {
				filteredNames.add(name);
			}
		}

		return filteredNames;
	}

	public List<View> extractViews(SchemaExtractionContext context) throws SQLException {
		return context.getViewFinder().findViews();
	}

	private List<Sequence> extractSequences(SchemaExtractionContext context) throws SQLException {
		return context.getSequenceFinder().findSequences();
	}

	private List<ForeignKey> extractForeignKeys(List<String> tableNames, SchemaExtractionContext context) throws SQLException {

		return ExtractionUtils.extractForeignKeys(tableNames, context.getSchemaName(), context.getDatabaseMetaData());
	}
}
