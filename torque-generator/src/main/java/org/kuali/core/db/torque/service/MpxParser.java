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

package org.kuali.core.db.torque.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.torque.engine.database.model.Column;
import org.kuali.common.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class parses a .mpx file and creates an in-memory representation of the data
 *
 * @author andrewlubbers
 */
public class MpxParser {

    public RowData parseMpxLine(List<Column> columns, String line) {
        RowData result = new RowData();

        String[] tokens = ImpexUtils.getOriginalValues(line);
        // make really sure that the tokens we parsed have the same size
        Assert.isTrue(columns.size() == tokens.length);

        List<DataBean> dataBeans = new ArrayList<DataBean>(tokens.length);
        int i = 0;
        for (; i < tokens.length; i++) {
            String token = tokens[i];
            Column column = columns.get(i);
            DataBean bean = processToken(column, token);
            dataBeans.add(bean);
        }

        result.setDataBeans(dataBeans);

        return result;
    }

    public DataBean processToken(Column column, String token) {
        DataBean result = new DataBean();

        result.setColumn(column);

        if (token == null) {
            result.setValue(null);
            result.setDateValue(null);
        }
        if (ImpexUtils.isColumnDateType(column)) {
            SimpleDateFormat sdf = new SimpleDateFormat(ImpexContext.MPX_DATE_FORMAT);
            Date parsedDate;
            try {
                parsedDate = sdf.parse(token);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Cannot parse " + token + " using format [" + ImpexContext.MPX_DATE_FORMAT + "]");
            }

            result.setValue(null);
            result.setDateValue(parsedDate);
        }
        if (column.needEscapedValue()) {
            String escaped1 = StringUtils.replace(token, "\\", "\\\\");
            String escaped2 = StringUtils.replace(escaped1, "'", "\\'");
            String escaped3 = StringUtils.replace(escaped2, "\n", "\\n");
            result.setValue("'" + escaped3 + "'");
            result.setDateValue(null);
        }

        return result;
    }
}
