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

import org.apache.torque.engine.database.model.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andy
 * Date: 2/14/13
 * Time: 6:34 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractImpexReader implements ImpexReader {

    private MpxParser mpxParser;

    protected final static String OUTPUT_DATE_FORMAT = "yyyyMMddHHmmss";

    @Override
    public String getInsertSql(Table table, BufferedReader reader, ImpexContext context) throws IOException {

        List<RowData> rows = mpxParser.parseMpx(ImpexUtils.getColumns(table), reader);

        return getInsertSql(table, rows, context);
    }

    protected abstract String getInsertSql(Table table, List<RowData> rows, ImpexContext context);

    public MpxParser getMpxParser() {
        return mpxParser;
    }

    public void setMpxParser(MpxParser mpxParser) {
        this.mpxParser = mpxParser;
    }

    protected boolean batchLimitReached(int rows, int length, ImpexContext context) {
        if (rows > context.getRowCountInterval()) {
            return false;
        }
        if (length > context.getDataSizeInterval()) {
            return false;
        }
        return true;
    }
}
