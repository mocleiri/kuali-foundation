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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.DefaultSqlReader;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This class provides a common framework for SqlConverter classes which merge insert statements into batch inserts
 *
 * @author andrewlubbers
 */
public abstract class AbstractInsertMergeSqlConverter implements SqlConverter {

    private static final Logger logger = LoggerFactory.getLogger(AbstractInsertMergeSqlConverter.class);

    private static final String LF = "\n";

    @Override
    public ConversionResult convert(ConversionContext context) {
        logger.debug("Converting {}", LocationUtils.getCanonicalPath(context.getOldFile()));
        File newFile = context.getNewFile();
        File oldFile = context.getOldFile();
        DefaultSqlReader reader = new DefaultSqlReader();
        reader.setDelimiter(context.getDelimiter());

        BufferedReader in = null;
        OutputStream out = null;
        try {
            in = LocationUtils.getBufferedReader(oldFile, context.getEncoding());
            out = FileUtils.openOutputStream(newFile);
            return convert(context, reader, in, out);
        } catch (IOException e) {
            throw new IllegalStateException("Unexpected IO error");
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }

    protected ConversionResult convert(ConversionContext context, SqlReader reader, BufferedReader in, OutputStream out) throws IOException {
        String sql = reader.getSqlStatement(in);
        while (sql != null) {
            String outputSql = getOutputSql(context, in, sql, reader);
            out.write(outputSql.getBytes(context.getEncoding()));
            sql = reader.getSqlStatement(in);
        }
        SqlMetaData before = getMetaData(context.getOldFile(), reader, context.getEncoding());
        SqlMetaData after = getMetaData(context.getNewFile(), reader, context.getEncoding());
        return new ConversionResult(context.getOldFile(), context.getNewFile(), before, after);
    }

    protected String getOutputSql(ConversionContext context, BufferedReader in, String sql, SqlReader reader) throws IOException {
        boolean insertStatement = isInsert(sql);
        if (insertStatement) {
            SqlInsertContext mc = new SqlInsertContext();
            mc.setSql(sql);
            mc.setReader(reader);
            mc.setInput(in);
            return combineInserts(context, mc);
        } else {
            // Add the sql followed by linefeed->delimiter->linefeed
            StringBuilder sqlBuilder = new StringBuilder(sql);
            sqlBuilder.append(getLineFeed()).append(context.getDelimiter()).append(getLineFeed());
            return sqlBuilder.toString();
        }
    }

    /**
     * Performs the real work of combining insert statements into batch inserts.
     *
     * @param conversionContext reference to meta data for this conversion process
     * @param sqlInsertContext contains the sql to convert and IO objects to write conversion results
     * @return converted sql
     */
    protected abstract String combineInserts(ConversionContext conversionContext, SqlInsertContext sqlInsertContext) throws IOException;

    protected boolean isInsert(String sql) {
        String trimmed = StringUtils.trim(sql);
        return StringUtils.startsWith(trimmed, getInsertPrefix());
    }

    /**
     * Returns the platform-specific prefix for an INSERT statement
     *
     * @return the prefix
     */
    public abstract String getInsertPrefix();

    protected SqlMetaData getMetaData(File file, SqlReader reader, String encoding) {
        BufferedReader in = null;
        try {
            in = LocationUtils.getBufferedReader(file, encoding);
            return reader.getSqlMetaData(in);
        } catch (IOException e) {
            throw new IllegalStateException("Unexpected IO error");
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public String getLineFeed() {
        return LF;
    }

    /**
     * A common test to determine if the current insert batch should continue adding data
     *
     * @param sql the remaining sql to process
     * @param count the number of inserts already processed
     * @param length the character length of the inserts already processed
     * @param context the metadata for this conversion
     * @return True if the current batch should be appended to, false otherwise
     */
    protected boolean continueBatch(String sql, int count, int length, ConversionContext context) {
        if (sql == null) {
            return false;
        }
        if (!isInsert(sql)) {
            return false;
        }
        if (count >= context.getMaxCount()) {
            return false;
        }
        if (length >= context.getMaxLength()) {
            return false;
        }
        return true;
    }
}
