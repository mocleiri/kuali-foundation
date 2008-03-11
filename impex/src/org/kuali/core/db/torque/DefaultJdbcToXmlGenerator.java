/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.core.db.torque;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.Project;
import org.apache.torque.engine.database.model.TypeMap;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.DocumentTypeImpl;
import org.apache.xerces.util.XMLChar;
import org.kuali.core.db.torque.JdbcCollectionService.TableIndex;
import org.w3c.dom.Element;

/**
 * 
 * This class...
 */
public class DefaultJdbcToXmlGenerator implements JdbcToXmlGenerator {
    private static Map<String, String> invalidSubStringMap = new HashMap<String, String>();
    static {
        invalidSubStringMap.put("$", "_DOLLAR_SIGN_");
    }

    private static final void log(String logMessage) {
        org.apache.commons.logging.LogFactory.getLog(DefaultJdbcToXmlGenerator.class).info(logMessage);
    }

    private static final void log(String logMessage, int level) {
        if (level == Project.MSG_WARN) {
            org.apache.commons.logging.LogFactory.getLog(DefaultJdbcToXmlGenerator.class).warn(logMessage);

        }
        else {
            org.apache.commons.logging.LogFactory.getLog(DefaultJdbcToXmlGenerator.class).debug(logMessage);
        }
    }

    private String dbUser;
    private boolean sameJavaName;

    private JdbcCollectionService collectionService;
    private DocumentImpl document;
    private String targetDatabase;


    /**
     * Oracle and other RDBMS are much more flexible than XML with naming. This means that there may be table names that don't
     * directly translate to XML element names because XML elements have a small set of allowable characters. This relies on a
     * <code>{@link Map}</code> to compare naming to make the table name more valid for XML
     * 
     * @param st table name
     * @return valid XML name that can be translated back to the original table name
     */
    private String tableNameEscape(String st) {
        if (XMLChar.isValidName(st)) {
            return st;
        }

        StringBuffer buff = new StringBuffer();
        for (char xmlChar : st.toCharArray()) {
            if (!XMLChar.isName(xmlChar)) {
                String replacement = invalidSubStringMap.get(new String(new char[] { xmlChar }));
                buff.append(replacement);
            }
            else {
                buff.append(xmlChar);
            }
        }

        return buff.toString();
    }

    /**
     * 
     * This method...
     * 
     * @param dbMetaData
     * @param tableList
     * @return
     * @throws SQLException
     */
    private Hashtable buildColumnTableMap(Collection<String> tableList) throws SQLException {
        Hashtable retval = new Hashtable();

        log("Building column/table map...");
        for (String curTable : tableList) {
            List<List> columns = getCollectionService().getColumns(curTable);

            for (List col : columns) {
                String name = (String) col.get(0);

                retval.put(name, curTable);
            }
        }

        return retval;
    }

    /**
     * 
     * <code>
     * // From DatabaseMetaData.java
     * //
     * // Indicates column might not allow NULL values. Huh?
     * // Might? Boy, that's a definitive answer.
     * int columnNoNulls = 0;
     *  
     * // Indicates column definitely allows NULL values.
     * int columnNullable = 1;
     * 
     * // Indicates NULLABILITY of column is unknown.
     * int columnNullableUnknown = 2; 
     * </code>
     * 
     * @param dbMetaData
     * @param curTable
     * @param table
     * @throws SQLException
     */
    private void generateColumnXml(String curTable, Element table) throws SQLException {
        List<List> columns = getCollectionService().getColumns(curTable);
        List<String> primaryKeys = getCollectionService().getPrimaryKeys(curTable);

        for (List col : columns) {
            String name = (String) col.get(0);
            Integer type = ((Integer) col.get(1));
            int size = ((Integer) col.get(2)).intValue();
            int scale = ((Integer) col.get(5)).intValue();


            Integer nullType = (Integer) col.get(3);
            String defValue = ((String) col.get(4));

            Element column = getDocument().createElement("column");
            column.setAttribute("name", name);
            if (isSameJavaName()) {
                column.setAttribute("javaName", name);
            }

            column.setAttribute("type", TypeMap.getTorqueType(type).getName());

            if (size > 0
                    && (type.intValue() == Types.CHAR || type.intValue() == Types.VARCHAR || type.intValue() == Types.LONGVARCHAR
                            || type.intValue() == Types.DECIMAL || type.intValue() == Types.NUMERIC)) {
                column.setAttribute("size", String.valueOf(size));
            }

            if (scale > 0 && (type.intValue() == Types.DECIMAL || type.intValue() == Types.NUMERIC)) {
                column.setAttribute("scale", String.valueOf(scale));
            }

            if (primaryKeys.contains(name)) {
                column.setAttribute("primaryKey", "true");
                // JHK: protect MySQL from excessively long column in the PK
                // System.out.println( curTable + "." + name + " / " + size );
                if (column.getAttribute("size") != null && size > 765) {
                    System.out.println("updating column " + curTable + "." + name + " length from " + size + " to 255");
                    column.setAttribute("size", "255");
                }
            }
            else {
                if (nullType.intValue() == DatabaseMetaData.columnNoNulls) {
                    column.setAttribute("required", "true");
                }
            }

            if (StringUtils.isNotEmpty(defValue)) {
                defValue = defValue.trim();
                // trim out parens & quotes out of def value.
                // makes sense for MSSQL. not sure about others.
                if (defValue.startsWith("(") && defValue.endsWith(")")) {
                    defValue = defValue.substring(1, defValue.length() - 1);
                }

                if (defValue.startsWith("'") && defValue.endsWith("'")) {
                    defValue = defValue.substring(1, defValue.length() - 1);
                }

                column.setAttribute("default", defValue);
            }
            table.appendChild(column);
        }
    }

    /**
     * @see org.kuali.core.db.torque.JdbcToXmlGenerator#generateXML(org.kuali.core.db.torque.JdbcCollectionService)
     */
    public DocumentImpl generateXML(JdbcCollectionService collectionService) throws SQLException {
        DocumentTypeImpl docType = new DocumentTypeImpl(null, "database", null, "database.dtd");
        setDocument(new DocumentImpl(docType));
        getDocument().appendChild(getDocument().createComment(" Autogenerated by KualiTorqueJDBCTransformTask! "));

        setCollectionService(collectionService);

        Element databaseNode = getDocument().createElement("database");
        databaseNode.setAttribute("name", dbUser);
        databaseNode.setAttribute("defaultJavaNamingMethod", "nochange");

        generateTableXml(databaseNode);

        generateViewXml(databaseNode);

        generateSequenceXml(databaseNode);

        getDocument().appendChild(databaseNode);


        return getDocument();
    }

    private void generateTableXml(Element databaseNode) throws SQLException {
        // The database map.
        List<String> tableList = getCollectionService().getTableNames();

        for (String curTable : tableList) {
            String xmlTable = curTable;
            if (!XMLChar.isValidName(xmlTable)) {
                xmlTable = tableNameEscape(xmlTable);
            }
            log("Processing table: " + curTable);

            Element table = getDocument().createElement("table");
            table.setAttribute("name", xmlTable);
            if (isSameJavaName()) {
                table.setAttribute("javaName", curTable);
            }

            generateColumnXml(curTable, table);

            generateForeignKeyXml(curTable, table);

            generateIndexXml(curTable, table);

            databaseNode.appendChild(table);
        }
    }

    /**
     * 
     * This method...
     * 
     * @param dbMetaData
     * @param curTable
     * @param table
     * @throws SQLException
     */
    private void generateForeignKeyXml(String curTable, Element table) throws SQLException {
        Map<String, Object[]> forgnKeys = getCollectionService().getForeignKeyMap(curTable);

        // Foreign keys for this table.
        for (Map.Entry<String, Object[]> foreignKey : forgnKeys.entrySet()) {
            Element fk = getDocument().createElement("foreign-key");
            fk.setAttribute("name", foreignKey.getKey());
            Object[] forKey = forgnKeys.get(foreignKey.getKey());
            String foreignKeyTable = (String) forKey[0];
            List refs = (List) forKey[1];
            fk.setAttribute("foreignTable", foreignKeyTable);
            String onDelete = (String) forKey[2];
            // gmcgrego - just adding onDelete if it's cascade so as not to affect kfs behavior
            if (onDelete == "cascade") {
                fk.setAttribute("onDelete", onDelete);
            }
            for (int m = 0; m < refs.size(); m++) {
                Element ref = getDocument().createElement("reference");
                String[] refData = (String[]) refs.get(m);
                ref.setAttribute("local", refData[0]);
                ref.setAttribute("foreign", refData[1]);
                fk.appendChild(ref);
            }
            table.appendChild(fk);
        }
    }

    /**
     * 
     * This method...
     * 
     * @param dbMetaData
     * @param curTable
     * @param table
     */
    private void generateIndexXml(String curTable, Element table) throws SQLException {
        for (TableIndex idx : getCollectionService().getIndexes(curTable)) {
            String tagName = idx.unique ? "unique" : "index";
            Element index = getDocument().createElement(tagName);
            index.setAttribute("name", idx.name);
            for (String colName : idx.columns) {
                Element col = getDocument().createElement(tagName + "-column");
                col.setAttribute("name", colName);
                index.appendChild(col);
            }
            table.appendChild(index);
        }
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public boolean isSameJavaName() {
        return sameJavaName;
    }

    public void setSameJavaName(boolean sameJavaName) {
        this.sameJavaName = sameJavaName;
    }

    public JdbcCollectionService getCollectionService() {
        return collectionService;
    }

    public void setCollectionService(JdbcCollectionService collectionService) {
        this.collectionService = collectionService;
    }

    public DocumentImpl getDocument() {
        return document;
    }

    public void setDocument(DocumentImpl document) {
        this.document = document;
    }

    private void generateViewXml(Element databaseNode) throws SQLException {
        List<String> viewNames = getCollectionService().getViewNames();

        for (String viewName : viewNames) {
            Element view = getDocument().createElement("view");
            view.setAttribute("name", viewName);

            view.setAttribute("viewdefinition", getCollectionService().getViewDefinition(viewName));
            databaseNode.appendChild(view);
        }
    }

    private void generateSequenceXml(Element databaseNode) throws SQLException {
        List<String> sequenceNames = getCollectionService().getSequenceNames();
        for (String sequenceName : sequenceNames) {
            Element sequence = getDocument().createElement("sequence");
            sequence.setAttribute("name", sequenceName);

            sequence.setAttribute("nextval", getCollectionService().getSequenceNextVal(sequenceName));

            databaseNode.appendChild(sequence);
        }
    }

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }
}
