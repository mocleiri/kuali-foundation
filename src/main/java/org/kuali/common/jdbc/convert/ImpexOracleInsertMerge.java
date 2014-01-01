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

import java.io.IOException;

/**
 * This class provides functionality to merge insert statements into batch insert statements from oracle impex sql files
 * within the SqlConverter framework
 *
 * @author andrewlubbers
 */
public class ImpexOracleInsertMerge extends AbstractInsertMergeSqlConverter {

    private static final String INSERT = "INSERT";
    private static final String DELIMITER = "/";
    private static final String LF = "\n";

    private static final String OPEN = "INSERT ALL" + LF;
    private static final String CLOSE = "SELECT * FROM DUAL" + LF + DELIMITER + LF;

    @Override
    protected String combineInserts(ConversionContext conversionContext, SqlInsertContext sqlInsertContext) throws IOException {
        String sql = sqlInsertContext.getSql();
        StringBuilder sb = new StringBuilder();
        sb.append(OPEN);
        String trimmed = StringUtils.trimToNull(sql);
        int length = sb.length();
        int count = 1;
        boolean proceed = continueBatch(trimmed, count, length, conversionContext);
        while (proceed) {
            String into = getIntoStatement(trimmed);
            count++;
            length += into.length();
            sb.append(into);
            sql = sqlInsertContext.getReader().getSqlStatement(sqlInsertContext.getInput());
            trimmed = StringUtils.trimToNull(sql);
            proceed = continueBatch(trimmed, count, length, conversionContext);
        }
        // The last SQL statement we read was an insert
        if (isInsert(trimmed)) {
            String into = getIntoStatement(trimmed);
            sb.append(into);
            count++;
        }
        sb.append(CLOSE);

        // There is a trailing SQL statement that is not an INSERT
        if (trimmed != null && !isInsert(trimmed)) {
            sb.append(sql).append(LF).append(DELIMITER).append(LF);
            count++;
        }

        return sb.toString();
    }

    @Override
    public String getInsertPrefix() {
        return INSERT;
    }

    protected String getIntoStatement(String trimmed) {
        return "  " + StringUtils.trim(StringUtils.substring(trimmed, INSERT.length())) + LF;
    }

}
