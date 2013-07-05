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

package org.kuali.common.impex.schema.impl.mysql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.impex.ProducerUtils;
import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.DataType;
import org.kuali.common.impex.model.Index;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.TypeSize;
import org.kuali.common.impex.model.UniqueConstraint;
import org.kuali.common.impex.model.util.ModelUtils;
import org.kuali.common.impex.schema.DataTypeMapping;
import org.kuali.common.impex.schema.impl.AbstractTableSqlProducer;
import org.kuali.common.util.CollectionUtils;

public class MySqlTableSqlProducer extends AbstractTableSqlProducer {

	protected static final String DROP_TABLE_HEADER = "DROP TABLE IF EXISTS ";
	protected static final String DROP_TABLE_FOOTER = "\n";

	protected static final String CREATE_TABLE_HEADER = "CREATE TABLE ";
	protected static final String CREATE_TABLE_FOOTER = " ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin\n";

	protected static final String TABLE_DETAILS_PREFIX = "\n(";
	protected static final String TABLE_DETAILS_SUFFIX = "\n)";
	protected static final String DEFAULT_PREFIX = " DEFAULT ";

	protected static final String COMMENT_PREFIX = "COMMENT = '";

	protected static final String CONSTRAINT_PREFIX = "CONSTRAINT ";
	protected static final String CONSTRAINT_SUFFIX = ")";
	protected static final String PRIMARY_KEY_CONSTRAINT = " PRIMARY KEY(";
	protected static final String UNIQUE_CONSTRAINT = " UNIQUE (";

	protected static final String INDEX_PREFIX = "INDEX ";
	protected static final String INDEX_COLUMNS_PREFIX = " (";
	protected static final String INDEX_SUFFIX = ")";

	/**
	 * MySQL data types
	 */
	protected static final String MYSQL_VARCHAR = "VARCHAR".intern();
	protected static final String MYSQL_CLOB = "LONGTEXT".intern();

	protected static final Map<DataType, String> MYSQL_DATATYPE_MAP = new HashMap<DataType, String>();

	static {
		MYSQL_DATATYPE_MAP.put(DataType.STRING, MYSQL_VARCHAR);
		MYSQL_DATATYPE_MAP.put(DataType.CLOB, MYSQL_CLOB);
	}

	protected static final Collection<String> INVALID_DEFAULT_VALUE_KEYWORDS;

	static {

		Collection<String> invalidKeywords = new ArrayList<String>();

		invalidKeywords.add("SYS_GUID()");
		invalidKeywords.add("SYSDATE");

		INVALID_DEFAULT_VALUE_KEYWORDS = Collections.unmodifiableCollection(invalidKeywords);
	}

	public List<String> getTablesSql(List<Table> tables) {
		List<String> results;

		if (tables == null || tables.isEmpty()) {
			return Collections.emptyList();
		}

		results = new ArrayList<String>();

		for (Table t : tables) {
			results.addAll(generateCreateTableStatements(t));
		}

		return results;
	}

	protected List<String> generateCreateTableStatements(Table t) {
		List<String> results = new ArrayList<String>();

		results.add(generateDropTableStatement(t));

		StringBuilder sb = new StringBuilder();

		sb.append(CREATE_TABLE_HEADER);
		sb.append(t.getName());

		sb.append(TABLE_DETAILS_PREFIX);
		sb.append(generateColumns(t));
		sb.append(generatePrimaryKeyConstraint(t));
		sb.append(generateUniqueConstraintDefinitions(t));
		sb.append(generateIndexDefinitions(t));
		sb.append(TABLE_DETAILS_SUFFIX);

		sb.append(CREATE_TABLE_FOOTER);

		sb.append(generateTableCommentString(t));

		results.add(sb.toString());

		return results;
	}

	protected String generateDropTableStatement(Table t) {
		StringBuilder sb = new StringBuilder();

		sb.append(DROP_TABLE_HEADER);
		sb.append(t.getName());
		sb.append(DROP_TABLE_FOOTER);

		return sb.toString();
	}

	protected String generateColumns(Table t) {
		StringBuilder sb = new StringBuilder();

		boolean firstColumn = true;
		for (Column column : t.getColumns()) {
			if (firstColumn) {
				firstColumn = false;
				sb.append(ProducerUtils.NEWLINE_TAB);
			} else {
				sb.append(ProducerUtils.COMMA);
				sb.append(ProducerUtils.NEWLINE_TAB);
			}
			sb.append(generateColumnDefinition(column));
		}

		return sb.toString();
	}

	protected String generateColumnDefinition(Column column) {
		StringBuilder sb = new StringBuilder();

		DataTypeMapping mapping = getMappingProvider().getDataTypeMapping(column);

		if (mapping != null) {
			sb.append(generateColumnDefinition(column, mapping));
		} else {
			sb.append(innerGenerateColumnDefinition(column));
		}

		return sb.toString();
	}

	private String innerGenerateColumnDefinition(Column column) {
		StringBuilder sb = new StringBuilder();

		// build the definition as <column name> <column type> <default value> <null/not null>
		sb.append(column.getName());
		sb.append(ProducerUtils.SPACE);

		// column type
		sb.append(translateDataType(column.getDataType()));

		TypeSize typeSize = column.getTypeSize();
		if (typeSize != null) {
			sb.append(ProducerUtils.TYPE_SIZE_PREFIX);
			sb.append(typeSize.getSize());
			if (typeSize.isScaleSet()) {
				sb.append(ProducerUtils.COMMA);
				sb.append(typeSize.getScale());
			}
			sb.append(ProducerUtils.TYPE_SIZE_SUFFIX);
		}

		// default value
		if (validDefaultValue(column.getDefaultValue())) {
			sb.append(DEFAULT_PREFIX);
			sb.append(column.getDefaultValue());
		}

		if (!column.isNullable()) {
			sb.append(ProducerUtils.SPACE).append(ProducerUtils.NOT_NULL);
		}

		if (StringUtils.isNotEmpty(column.getDescription())) {
			sb.append(ProducerUtils.SPACE).append(COMMENT_PREFIX).append(column.getDescription()).append(ProducerUtils.SINGLE_QUOTE);
		}

		return sb.toString();
	}

	protected boolean validDefaultValue(String defaultValue) {
		if (StringUtils.isNotEmpty(defaultValue)) {
			if (INVALID_DEFAULT_VALUE_KEYWORDS.contains(defaultValue)) {

			}
		}

		return false;
	}

	protected String translateDataType(DataType dataType) {
		if (MYSQL_DATATYPE_MAP.containsKey(dataType)) {
			return MYSQL_DATATYPE_MAP.get(dataType);
		}

		return dataType.name();
	}

	/**
	 * This method should incorporate information from the given DataTypeMapping to override information from the Column model as appropriate to generate an oracle column
	 * definition.
	 * 
	 * TODO KSENROLL-303 Mappings for data types that Hibernate expects from MySQL for KS need to be mapped
	 * 
	 * @param column
	 *            the model data of the Column
	 * @param mapping
	 *            the mapping data
	 * 
	 * @return sql snippet of the column definition
	 */
	protected String generateColumnDefinition(Column column, DataTypeMapping mapping) {
		if (mapping.getSql() == null) {
			StringBuilder sb = new StringBuilder();

			DataType newDataType = column.getDataType();
			if (mapping.getDataType() != null) {
				newDataType = mapping.getDataType();
			}

			Column newCol = new Column(column.getName(), newDataType, column.getTable());

			newCol.setTypeSize(mapping.getTypeSize());

			sb.append(innerGenerateColumnDefinition(newCol));

			return sb.toString();
		} else {
			return mapping.getSql();
		}
	}

	protected String generatePrimaryKeyConstraint(Table t) {
		StringBuilder sb = new StringBuilder();

		String primaryKeysString = ModelUtils.getCsvPrimaryKeyColumnNames(t);

		if (StringUtils.isNotEmpty(primaryKeysString)) {
			sb.append(ProducerUtils.COMMA);
			sb.append(ProducerUtils.NEWLINE_TAB);
			sb.append(CONSTRAINT_PREFIX);
			sb.append(ProducerUtils.generatePrimaryKeyName(t));
			sb.append(PRIMARY_KEY_CONSTRAINT);
			sb.append(primaryKeysString);
			sb.append(CONSTRAINT_SUFFIX);
		}

		return sb.toString();
	}

	protected String generateUniqueConstraintDefinitions(Table t) {
		StringBuilder sb = new StringBuilder();

		for (UniqueConstraint uc : CollectionUtils.toEmptyList(t.getUniqueConstraints())) {
			sb.append(ProducerUtils.COMMA);
			sb.append(ProducerUtils.NEWLINE_TAB);
			sb.append(CONSTRAINT_PREFIX);
			sb.append(uc.getName());
			sb.append(UNIQUE_CONSTRAINT);
			sb.append(CollectionUtils.getCSV(uc.getColumnNames()));
			sb.append(CONSTRAINT_SUFFIX);
		}

		return sb.toString();
	}

	protected String generateIndexDefinitions(Table t) {
		StringBuilder sb = new StringBuilder();

		for (Index index : CollectionUtils.toEmptyList(t.getIndices())) {
			sb.append(ProducerUtils.COMMA);
			sb.append(ProducerUtils.NEWLINE_TAB);
			sb.append(INDEX_PREFIX);
			sb.append(index.getName());
			sb.append(INDEX_COLUMNS_PREFIX);
			sb.append(CollectionUtils.getCSV(index.getColumnNames()));
			sb.append(INDEX_SUFFIX);
		}

		return sb.toString();
	}

	protected String generateTableCommentString(Table t) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotEmpty(t.getDescription())) {
			sb.append(ProducerUtils.COMMA).append(ProducerUtils.SPACE);
			sb.append(COMMENT_PREFIX);
			sb.append(ProducerUtils.SINGLE_QUOTE);
			sb.append(t.getDescription());
			sb.append(ProducerUtils.SINGLE_QUOTE);
		}

		return sb.toString();
	}

}
