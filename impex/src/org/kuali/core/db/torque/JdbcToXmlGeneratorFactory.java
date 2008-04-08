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

import static org.kuali.core.db.torque.FormattedLogger.*;

/**
 * 
 * This class...
 */
public final class JdbcToXmlGeneratorFactory {
    private static final String PROPERTY_SET_MESSAGE = "An exception occurred setting property %s, on class %s. The exception was %s with message '%s'";
    private static final String CLASS_LOOKUP_MESSAGE = "An exception occurred finding class %s. The exception was %s with message '%s'";
    private static JdbcToXmlGeneratorFactory instance;
    private static final String IMPEX_PROPERTY_PREFIX = "impex.generator.";
    private static final String IMPEX_GENERATOR_SERVICE_PACKAGE_PREFIX = "package";
    
    
    private Hashtable<String, String> instanceProperties;
    
    public JdbcToXmlGeneratorFactory() {
        instanceProperties = new Hashtable<String, String>();
    }

    public static JdbcToXmlGeneratorFactory getInstance() {
        if (instance == null) {
            instance = new JdbcToXmlGeneratorFactory();
        }

        return instance;
    }
    
    public static JdbcToXmlGeneratorFactory getInstance(Hashtable<String, String> props) {
        JdbcToXmlGeneratorFactory retval = getInstance();

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
                    warn(PROPERTY_SET_MESSAGE, property.getKey(), getClass().getSimpleName(), e.getClass().getSimpleName(), e.getMessage());
                }
            }
        }
    }

    /**
     * Create a <code>{@link JdbcToXmlGenerator}</code> instance by the name specified. The name is the simplename of the class
     * to use.
     * 
     * @param jdbcToXmlGeneratorName
     * @return JdbcCollecitonService
     */
    public JdbcToXmlGenerator getGenerator(String jdbcToXmlGeneratorName) {
        JdbcToXmlGenerator retval = null;
        String fullClassName = getInstanceProperties().get(IMPEX_PROPERTY_PREFIX + IMPEX_GENERATOR_SERVICE_PACKAGE_PREFIX);

        if (StringUtils.isNotBlank(jdbcToXmlGeneratorName)) {
            fullClassName += jdbcToXmlGeneratorName;
        }
        else {
            fullClassName += getInstanceProperties().get(IMPEX_PROPERTY_PREFIX + "default");
        }
        
        try {
            retval = (JdbcToXmlGenerator) Class.forName(fullClassName).newInstance();
        }
        catch (ClassNotFoundException cnfe) {
            warn(CLASS_LOOKUP_MESSAGE, fullClassName, cnfe.getClass().getSimpleName(), cnfe.getMessage());
        }
        catch (Exception e) {
            warn(CLASS_LOOKUP_MESSAGE, fullClassName, e.getClass().getSimpleName(), e.getMessage());
        }
        
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
        // TODO Auto-generated method stub
        return 0;
    }
}
