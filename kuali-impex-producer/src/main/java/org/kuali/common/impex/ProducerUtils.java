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

package org.kuali.common.impex;

import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.kuali.common.impex.model.DataType;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Table;
import org.kuali.common.util.LocationUtils;

/**
 * String utility methods for classes creating sql from schema model data
 */
public class ProducerUtils {

    // Constants in common between many producer classes
    public static final String NEWLINE = "\n";
    public static final String COMMA = ",";
    public static final String DOT = ".";
    public static final String SPACE = " ";
    public static final String STATEMENT_SEPARATOR = "/";

    /**
     * This value was retrieved from the Velocity templates that were generating
     * the schema sqls in previous versions of Impex.
     *
     * No idea on why this value was chosen, though it's safe to guess it ensures
     * a primary key name that is of acceptable length to many DB vendors
     */
    private static final int MAX_TABLE_NAME_SNIPPET_SIZE_FOR_PK = 27;

    private static final String PRIMARY_KEY_SUFFIX = "P1";
    public static final char SINGLE_QUOTE = '\'';
    public static final String TYPE_SIZE_PREFIX = "(";
    public static final String TYPE_SIZE_SUFFIX = ")";
    public static final String NOT_NULL = "NOT NULL";
    public static final String NEWLINE_TAB = "\n\t";

    public static String generatePrimaryKeyName(Table t) {
        StringBuilder sb = new StringBuilder();

        // truncate table name to MAX_TABLE_NAME_SNIPPET_SIZE_FOR_PK
        String truncated = t.getName();
        if(truncated.length() > MAX_TABLE_NAME_SNIPPET_SIZE_FOR_PK) {
            truncated = truncated.substring(0, MAX_TABLE_NAME_SNIPPET_SIZE_FOR_PK);
        }

        sb.append(truncated).append(PRIMARY_KEY_SUFFIX);

        return sb.toString();
    }

    public static boolean isDateType(DataType dataType) {
        if(dataType == null) {
            return false;
        }

        return dataType == DataType.DATE || dataType == DataType.TIMESTAMP;
    }

    public static boolean isTextType(DataType dataType) {
        if(dataType == null) {
            return false;
        }

        return dataType == DataType.STRING || dataType == DataType.CLOB;
    }

    /**
     * Standard code for initializing a schema model from an xml resource
     *
     * @param xmlLocation resource location of the xml
     * @return a Schema populated from the xml
     * @throws JAXBException
     * @throws IOException
     */
    public static Schema unmarshalSchema(String xmlLocation) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Schema.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (Schema)unmarshaller.unmarshal(LocationUtils.getBufferedReader(xmlLocation));
    }
}
