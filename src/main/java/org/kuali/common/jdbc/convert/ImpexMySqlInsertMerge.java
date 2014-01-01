/**
 * Copyright 2010-2014 The Kuali Foundation
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
package org.kuali.common.jdbc.convert;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ImpexMySqlInsertMerge extends AbstractInsertMergeSqlConverter {

	private static final Logger logger = LoggerFactory.getLogger(ImpexMySqlInsertMerge.class);
	private static final String INSERT = "INSERT INTO";
	private static final String VALUES_TOKEN = ")\n  VALUES (";

	protected String getInsertIntoValuesClause(String trimmed) {
		int pos = StringUtils.indexOf(trimmed, VALUES_TOKEN);
		String s = StringUtils.substring(trimmed, 0, pos);
		return s + ") VALUES ";
	}

	protected String getValues(String trimmed) {
		int pos = StringUtils.indexOf(trimmed, VALUES_TOKEN);
		if (pos == -1) {
			throw new IllegalArgumentException("Unable to parse INSERT statement: " + trimmed);
		}
		return "(" + StringUtils.substring(trimmed, pos + VALUES_TOKEN.length());
	}

	protected void appendValues(StringBuilder sb, int count, String trimmed) {
		String values = getValues(trimmed);
		// This is the exact same thing mysqldump does when dumping a db
		String escaped = StringUtils.replace(values, "\n", "\\n");
		if (count > 1) {
			sb.append(",");
		}
		sb.append(escaped);
	}

	@Override
    protected String combineInserts(ConversionContext cc, SqlInsertContext context) throws IOException {
		logger.debug("Combining insert statements");
		String sql = context.getSql();
		StringBuilder sb = new StringBuilder();
		String trimmed = StringUtils.trimToNull(sql);
		String insertIntoValues = getInsertIntoValuesClause(trimmed);
		sb.append(insertIntoValues);
		int count = 1;
		boolean proceed = continueBatch(trimmed, count, sb.length(), cc);
		while (proceed) {
			appendValues(sb, count, trimmed);
			count++;
			sql = context.getReader().getSqlStatement(context.getInput());
			trimmed = StringUtils.trimToNull(sql);
			proceed = continueBatch(trimmed, count, sb.length(), cc);
		}
		// The last SQL statement we read was an insert
		if (isInsert(trimmed)) {
			appendValues(sb, count, trimmed);
			count++;
		}
		sb.append(getLineFeed()).append(cc.getDelimiter()).append(getLineFeed());

		// There is a trailing SQL statement that is not an INSERT
		if (trimmed != null && !isInsert(trimmed)) {
			sb.append(sql).append(getLineFeed()).append(cc.getDelimiter()).append(getLineFeed());
			count++;
		}
		return sb.toString();
	}

	@Override
    protected boolean continueBatch(String sql, int count, int length, ConversionContext context) {
		return super.continueBatch(sql, count, length, context);
	}

	@Override
	public String getInsertPrefix() {
		return INSERT;
	}

}
