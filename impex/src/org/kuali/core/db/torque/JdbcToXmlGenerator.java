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

import java.sql.SQLException;
import java.util.Hashtable;

import org.apache.xerces.dom.DocumentImpl;

/**
 * 
 * This class...
 */
public interface JdbcToXmlGenerator {

    public DocumentImpl generateXML(JdbcCollectionService jdbcCollections) throws SQLException;

    public String getDbUser();

    public void setDbUser(String dbUser);

    public boolean isSameJavaName();

    public void setSameJavaName(boolean sameJavaName);

    public JdbcCollectionService getCollectionService();

    public void setCollectionService(JdbcCollectionService collectionService);

    public DocumentImpl getDocument();

    public void setDocument(DocumentImpl document);

}
