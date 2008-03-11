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

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Hashtable;

import org.apache.tools.ant.BuildException;
import org.apache.torque.task.TorqueJDBCTransformTask;
import org.apache.xml.serialize.Method;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

/**
 * 
 * This class...
 */
public class KualiTorqueJdbcTransformTask extends TorqueJDBCTransformTask {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KualiTorqueJdbcTransformTask.class);

    private String jdbcToXmlGeneratorName;
    private String jdbcCollectionServiceName;

    public String getJdbcCollectionServiceName() {
        return jdbcCollectionServiceName;
    }

    public void setJdbcCollectionServiceName(String jdbcCollectionServiceName) {
        this.jdbcCollectionServiceName = jdbcCollectionServiceName;
    }

    public String getJdbcToXmlGeneratorName() {
        return jdbcToXmlGeneratorName;
    }

    public void setJdbcToXmlGeneratorName(String jdbcToXmlGeneratorName) {
        this.jdbcToXmlGeneratorName = jdbcToXmlGeneratorName;
    }
    
    /**
     * Adds database information on top of normal project properties. Clones the <code>{@link Hashtable}</code> containing the
     * project properties, so that they actual properties class is not modified.
     * 
     * @return Hashtable for properties
     */
    private Hashtable<String, String> customizedProjectProperties() {
        Hashtable<String, String> retval = (Hashtable<String, String>) getProject().getProperties().clone();

        retval.put("dbDriver", dbDriver);
        retval.put("dbUrl", dbUrl);
        retval.put("dbUser", dbUser);
        retval.put("dbPassword", dbPassword);
        retval.put("dbSchema", dbSchema);
        retval.put("targetDatabase", getProject().getProperty("torque.database"));
        
        return retval;
    }

    /**
     * Default constructor.
     * 
     * @throws BuildException
     */
    public void execute() throws BuildException {
        log("Torque - JDBCToXMLSchema starting");
        log("Your DB settings are:");
        log("driver : " + dbDriver);
        log("URL : " + dbUrl);
        log("user : " + dbUser);
        // log("password : " + dbPassword);
        log("schema : " + dbSchema);
        

        JdbcCollectionServiceFactory collectionServiceFactory = JdbcCollectionServiceFactory.getInstance(customizedProjectProperties());
        JdbcCollectionService jdbcCollections = collectionServiceFactory.getCollectionService(getJdbcCollectionServiceName());
        JdbcToXmlGeneratorFactory generatorFactory = JdbcToXmlGeneratorFactory.getInstance(customizedProjectProperties());
        JdbcToXmlGenerator generator = generatorFactory.getGenerator(getJdbcToXmlGeneratorName());

        try {
            jdbcCollections.openConnection();
            doc = generator.generateXML(jdbcCollections);
            log(xmlSchema);
            XMLSerializer xmlSerializer = new XMLSerializer(new PrintWriter(new FileOutputStream(xmlSchema)), new OutputFormat(
                Method.XML, null, true));
            xmlSerializer.serialize(doc);
        }
        catch (Exception e) {
            jdbcCollections.closeConnection();
            throw new BuildException(e);
        }
        log("Torque - JDBCToXMLSchema finished");
    }
}
