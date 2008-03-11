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

import java.util.Hashtable;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import static org.kuali.core.db.torque.LogManager.exception;

/**
 * 
 * This class...
 */
public final class JdbcCollectionServiceFactory implements Loggable {
    private static JdbcCollectionServiceFactory instance;
    private static final String IMPEX_PROPERTY_PREFIX = "impex.collection.";
    private static final String IMPEX_PACKAGE_PREFIX = "package";
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(JdbcCollectionServiceFactory.class);

    private Hashtable<String, String> instanceProperties;
    
    /** JDBC URL. */
    private String dbUrl;

    /** JDBC driver. */
    private String dbDriver;

    /** JDBC user name. */
    private String dbUser;

    /** JDBC password. */
    private String dbPassword;

    /** DB schema to use. */
    private String dbSchema;
    
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbSchema() {
        return dbSchema;
    }

    public void setDbSchema(String dbSchema) {
        this.dbSchema = dbSchema;
    }

    public JdbcCollectionServiceFactory() {
        instanceProperties = new Hashtable<String, String>();
    }

    public static JdbcCollectionServiceFactory getInstance() {
        if (instance == null) {
            instance = new JdbcCollectionServiceFactory();
        }

        return instance;
    }

    public static JdbcCollectionServiceFactory getInstance(Hashtable<String, String> props) {
        JdbcCollectionServiceFactory retval = getInstance();

        retval.setInstanceProperties(props);
        
        retval.fillProperties();
        
        return retval;
    }

    /**
     * Assigns instance properties from a <code>{@link Hashtable}</code>. If the name of the property does not contain a
     * &quot;.&quot;, then it is considered an instance property and is assigned on the object directly
     */
    private void fillProperties() {
        for (Entry<String, String> property : getInstanceProperties().entrySet()) {
            if (PropertyUtils.isWriteable(this, property.getKey())) {
                try {
                    PropertyUtils.setProperty(this, property.getKey(), property.getValue());
                }
                catch (Exception e) {
                    exception(e);
                }
            }
        }
    }

    /**
     * Create a <code>{@link JdbcCollectionService}</code> instance by the name specified. The name is the simplename of the class
     * to use.
     * 
     * @param jdbcCollectionServiceName
     * @return JdbcCollecitonService
     */
    public JdbcCollectionService getCollectionService(String jdbcCollectionServiceName) {
        JdbcCollectionService retval = null;
        String fullClassName = getInstanceProperties().get(IMPEX_PROPERTY_PREFIX + IMPEX_PACKAGE_PREFIX);

        if (StringUtils.isNotBlank(jdbcCollectionServiceName)) {
            fullClassName += jdbcCollectionServiceName;
        }
        else {
            fullClassName += getInstanceProperties().get(IMPEX_PROPERTY_PREFIX + "default").toString();
        }

        try {
            retval = (JdbcCollectionService) Class.forName(fullClassName).newInstance();
        }
        catch (ClassNotFoundException cnfe) {
            exception(cnfe);
        }
        catch (Exception e) {
            exception(e);
        }
        
        retval.setDbDriver(getDbDriver());
        retval.setDbPassword(getDbPassword());
        retval.setDbUrl(getDbUrl());
        retval.setDbUser(getDbUser());
        retval.setDbSchema(getDbSchema());
        retval.setTargetDatabase(getTargetDatabase());
        
        return retval;
    }

    public Hashtable<String, String> getInstanceProperties() {
        return instanceProperties;
    }

    public void setInstanceProperties(Hashtable<String, String> instanceProperties) {
        this.instanceProperties = instanceProperties;
    }

    public String getLogMessage(Object... objs) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getStackOutputLevel() {
        return 0;
    }
}
