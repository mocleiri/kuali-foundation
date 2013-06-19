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

package org.kuali.common.impex.data.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kuali.common.impex.ProducerUtils;
import org.kuali.common.impex.data.MpxHeaderData;
import org.kuali.common.impex.data.SqlProducer;
import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.DataType;

/**
 * @author andrewlubbers
 */
public abstract class AbstractSqlProducer implements SqlProducer {

    protected final static String OUTPUT_DATE_FORMAT = "yyyyMMddHHmmss";
    public static final int DEFAULT_BATCH_ROW_COUNT_LIMIT = 50;
    public static final int DEFAULT_DATA_SIZE_LIMIT = 50 * 1024;

    int batchRowCountLimit = DEFAULT_BATCH_ROW_COUNT_LIMIT;
    int batchDataSizeLimit = DEFAULT_DATA_SIZE_LIMIT;

    protected boolean batchLimitReached(int rows, int length) {
        if (rows > getBatchRowCountLimit()) {
            return true;
        } else if (length > getBatchDataSizeLimit()) {
            return true;
        }

        return false;
    }

    protected abstract String getEscapedValue(Column column, String token);

    protected List<DataBean> buildRowData(List<Column> columns, String[] tokens, MpxHeaderData headerData) {
        List<DataBean> result = new ArrayList<DataBean>();

        // tokens are listed in the order of column names from the header data
        // So to find the right column definition, we need to order them the same as the header data
        List<Column> sortedColumns = new ArrayList<Column>(columns.size());
        for (String colName : headerData.getColumnNames()) {
            // find the matching column definition
            Column foundColumn = null;
            for(Column c : columns) {
                if (c.getName().equals(colName)) {
                    foundColumn = c;
                }
            }

            if(foundColumn == null) {
                throw new RuntimeException("No column definition found for column name from header: " + colName);
            }

            sortedColumns.add(foundColumn);
        }

        // process the tokens with column definitions from the sorted list
        for (int i = 0; i < tokens.length; i++) {
            result.add(processToken(sortedColumns.get(i), tokens[i]));
        }
        return result;
    }

    public DataBean processToken(Column column, String token) {
        DataBean result = new DataBean();

        result.setColumn(column);

        if (token == null) {
            result.setValue(null);
            result.setDateValue(null);
        } else if (ProducerUtils.isDateType(column.getColumnDataType())) {
            Date parsedDate = getDate(token);
            result.setValue(null);
            result.setDateValue(parsedDate);
        } else if (column.getColumnDataType() == DataType.STRING || column.getColumnDataType() == DataType.CLOB) {
            result.setValue(getEscapedValue(column, token));
            result.setDateValue(null);
        } else {
            result.setDateValue(null);
            result.setValue(token);
        }

        return result;
    }

    protected Date getDate(String token) {
        SimpleDateFormat sdf = new SimpleDateFormat(ParseUtils.MPX_DATE_FORMAT);
        try {
            return sdf.parse(token);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Cannot parse [" + token + "] using format [" + ParseUtils.MPX_DATE_FORMAT + "]");
        }
    }

    @Override
    public int getBatchRowCountLimit() {
        return batchRowCountLimit;
    }

    @Override
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

}
