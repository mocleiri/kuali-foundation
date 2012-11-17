package org.apache.ojb.broker.platforms;

/* Copyright 2002-2005 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Properties;
 
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ojb.broker.util.sequence.SequenceManagerHelper;

/**
 * This class defines specific behavior for the H2 platform.
 */
public class PlatformH2Impl extends PlatformDefaultImpl {

    @Override
    public byte getJoinSyntaxType() {
        return SQL92_NOPAREN_JOIN_SYNTAX;
    }

    @Override
    public String createSequenceQuery(String sequenceName) {
        return "CREATE SEQUENCE " + sequenceName;
    }

    @Override
    public String createSequenceQuery(String sequenceName, Properties prop) {
        /*
        CREATE SEQUENCE [schema.]sequence
            [START WITH integer]
            [INCREMENT BY integer]
            [CACHE integer]
        */
        final StringBuilder query = new StringBuilder(createSequenceQuery(sequenceName));
        if(prop != null) {
            Long value;

            value = SequenceManagerHelper.getSeqStart(prop);
            if(value != null) {
                query.append(" START WITH ").append(value.longValue());
            }

            value = SequenceManagerHelper.getSeqIncrementBy(prop);
            if(value != null) {
                query.append(" INCREMENT BY ").append(value.longValue());
            }

            value = SequenceManagerHelper.getSeqCacheValue(prop);
            if(value != null) {
                query.append(" CACHE ").append(value.longValue());
            }
        }
        return query.toString();
    }

    @Override
    public String nextSequenceQuery(String sequenceName) {
        return "select " + sequenceName + ".nextval from dual";
    }

    @Override
    public String dropSequenceQuery(String sequenceName) {
        return "drop sequence " + sequenceName;
    }

    @Override
    public void setObjectForStatement(final PreparedStatement ps, final int index, final Object val, final int sqlType) throws SQLException
    {
        final Object value = convertValue(val);

        if (!isSupportedType(value)) {
            log.warn("Invalid type [index: " + index + " value: " + value + " value class: " + ((value != null) ? value.getClass().getName() : "null") + " sqlType: " + sqlType + "]");
        }

        switch (sqlType) {
            case Types.BIT :
                ps.setObject(index, value);
                break;
            case Types.CLOB :
                Reader reader = null;
                int length = 0;
                if (value instanceof String) {
                    reader = new StringReader((String) value);
                    length = (((String) value)).length();
                } else if (value instanceof char[]) {
                    String string = new String((char[])value);
                    reader = new StringReader(string);
                    length = string.length();
                } else if (value instanceof byte[]) {
                    byte buf[] = (byte[]) value;
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(buf);
                    reader = new InputStreamReader(inputStream);
                }

                ps.setCharacterStream(index, reader, length);
                break;
            default :
                super.setObjectForStatement(ps, index, value, sqlType);

        }
    }

    /**
     * Converts a value without considering jdbc type to a support type.
     * @param val the value
     * @return the converted value
     */
    private Object convertValue(Object val) {
        if (val instanceof BigInteger) {
            return new BigDecimal((BigInteger) val);
        }
        return val;
    }

    /**
     * Checks if a type is one of the directly supported types.  Does not take into account custom java types.
     * @return true is a support type.
     */
    private boolean isSupportedType(Object value) {
        return value == null || value instanceof java.lang.Integer || value instanceof java.lang.Boolean || value instanceof java.lang.Short ||
               value instanceof java.lang.Long || value instanceof java.math.BigDecimal || value instanceof java.lang.Double ||
               value instanceof java.lang.Float || value instanceof java.sql.Time || value instanceof java.sql.Date ||
               value instanceof java.sql.Timestamp || value instanceof byte[] || value instanceof java.lang.String ||
               value instanceof java.sql.Blob || value instanceof java.io.InputStream || value instanceof java.sql.Clob ||
               value instanceof java.io.Reader || value instanceof java.util.UUID;
    }
}
