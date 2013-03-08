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

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.torque.engine.database.model.Column;
import org.apache.torque.engine.database.model.Table;
import org.kuali.common.util.CollectionUtils;

/**
 * @author andrewlubbers
 */
public abstract class AbstractSqlProducer implements SqlProducer {

	protected final static String OUTPUT_DATE_FORMAT = "yyyyMMddHHmmss";
	public static final int DEFAULT_BATCH_ROW_COUNT_LIMIT = 50;
	public static final int DEFAULT_DATA_SIZE_LIMIT = 50 * 1024;

	private int batchRowCountLimit = DEFAULT_BATCH_ROW_COUNT_LIMIT;
	private int batchDataSizeLimit = DEFAULT_DATA_SIZE_LIMIT;

	protected boolean batchLimitReached(int rows, int length) {
		if (rows > getBatchRowCountLimit()) {
			return true;
		} else if (length > getBatchDataSizeLimit()) {
			return true;
		}

		return false;
	}

	protected String readLineSkipHeader(BufferedReader reader) throws IOException {
		// First check to see if the reader is at the Header line.
		// If it is, skip that line
		String line = reader.readLine();
		if (ImpexUtils.isHeaderLine(line)) {
			line = reader.readLine();
		}

		return line;
	}

	protected abstract String getEscapedValue(Column column, String token);

	protected List<DataBean> buildRowData(List<Column> columns, String[] tokens) {
		List<DataBean> result = new ArrayList<DataBean>();

		for (int i = 0; i < tokens.length; i++) {
			result.add(processToken(columns.get(i), tokens[i]));
		}

		return result;
	}

	public DataBean processToken(Column column, String token) {
		DataBean result = new DataBean();

		result.setColumn(column);

		if (token == null) {
			result.setValue(null);
			result.setDateValue(null);
		} else if (ImpexUtils.isColumnDateType(column)) {
			SimpleDateFormat sdf = new SimpleDateFormat(ImpexContext.MPX_DATE_FORMAT);
			Date parsedDate;
			try {
				parsedDate = sdf.parse(token);
			} catch (ParseException e) {
				throw new IllegalArgumentException("Cannot parse " + token + " using format [" + ImpexContext.MPX_DATE_FORMAT + "]");
			}

			result.setValue(null);
			result.setDateValue(parsedDate);
		} else if (column.needEscapedValue()) {
			result.setValue(getEscapedValue(column, token));
			result.setDateValue(null);
		} else {
			result.setDateValue(null);
			result.setValue(token);
		}

		return result;
	}

	public int getBatchRowCountLimit() {
		return batchRowCountLimit;
	}

	public int getBatchDataSizeLimit() {
		return batchDataSizeLimit;
	}

	@Override
	public void setBatchDataSizeLimit(int batchDataSizeLimit) {
		this.batchDataSizeLimit = batchDataSizeLimit;
	}

	@Override
	public void setBatchRowCountLimit(int batchRowCountLimit) {
		this.batchRowCountLimit = batchRowCountLimit;
	}

	protected String getColumnNamesCSV(Table table) {
		List<Column> columns = ImpexUtils.getColumns(table);
		List<String> colNames = new ArrayList<String>(columns.size());
		for (Column col : columns) {
			colNames.add(col.getName());
		}

		return CollectionUtils.getCSV(colNames);
	}
}
