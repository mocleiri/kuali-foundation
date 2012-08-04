/**
 * Copyright 2009-2012 The Kuali Foundation
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
package org.codehaus.mojo.properties;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.StringUtils;

/**
 * Writes project properties to a file.
 * 
 * @author <a href="mailto:zarars@gmail.com">Zarar Siddiqi</a>
 * @version $Id: WriteProjectProperties.java 9747 2009-05-20 13:27:44Z mark $
 * @goal write-project-properties
 */
public class WriteProjectProperties extends AbstractWritePropertiesMojo {
    private static final String[] ESCAPE_CHARS = { "\n", "\r", "\t", ":", "#", "=" };

    /**
     * If true, the plugin will create the properties file formatted the same way Ant formats properties files using the
     * <code>echoproperties</code> task. This mode adds 3 custom properties at the top of the file, DSTAMP, TODAY, and
     * TSTAMP
     * 
     * @parameter default-value="false" expression="${properties.antEchoPropertiesMode}"
     */
    private boolean antEchoPropertiesMode;

    /**
     * If true, the plugin will include system properties when writing the properties file. System properties override
     * both environment variables and project properties.
     * 
     * @parameter default-value="false" expression="${properties.includeSystemProperties}"
     */
    private boolean includeSystemProperties;

    /**
     * If true, the plugin will include environment variables when writing the properties file. Environment variables
     * are prefixed with "env". Environment variables override project properties.
     * 
     * @parameter default-value="false" expression="${properties.includeEnvironmentVariables}"
     */
    private boolean includeEnvironmentVariables;

    /**
     * Comma separated set of properties to exclude when writing the properties file
     * 
     * @parameter expression="${properties.exclude}"
     */
    private String exclude;

    /**
     * Comma separated set of properties to write to the properties file. If provided, only the properties matching
     * those supplied here will be written to the properties file.
     * 
     * @parameter expression="${properties.include}"
     */
    private String include;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Properties properties = new Properties();
        // Add project properties
        properties.putAll(project.getProperties());
        if (includeEnvironmentVariables) {
            // Add environment variables, overriding any project properties with the same key
            properties.putAll(getEnvironmentVariables());
        }
        if (includeSystemProperties) {
            // Add system properties, overriding any project properties with the same key
            properties.putAll(System.getProperties());
        }
        // Remove properties as appropriate
        trim(properties, exclude, include);
        getLog().info("Creating " + outputFile);
        if (antEchoPropertiesMode) {
            echoPropertiesMode(outputFile, properties);
        } else {
            writeProperties(outputFile, null, properties);
        }
    }

    protected Properties getEnvironmentVariables() {
        String prefix = "env";
        Map<String, String> map = System.getenv();
        Properties props = new Properties();
        for (String key : map.keySet()) {
            String newKey = prefix + "." + key;
            String value = map.get(key);
            props.setProperty(newKey, value);
        }
        return props;
    }

    protected void trim(Properties properties, String omitCSV, String includeCSV) {
        List<String> omitKeys = ReadPropertiesMojo.getListFromCSV(omitCSV);
        for (String key : omitKeys) {
            properties.remove(key);
        }
        if (StringUtils.isBlank(includeCSV)) {
            return;
        }
        List<String> includeKeys = ReadPropertiesMojo.getListFromCSV(includeCSV);
        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            if (!includeKeys.contains(key)) {
                properties.remove(key);
            }
        }
    }

    protected void echoPropertiesMode(File file, Properties properties) throws MojoExecutionException {
        SimpleDateFormat dstamp = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat today = new SimpleDateFormat("MMMM d yyyy");
        SimpleDateFormat tstamp = new SimpleDateFormat("HHmm");
        Date now = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("#Ant properties\n");
        sb.append("#" + now + "\n");
        sb.append("DSTAMP=" + dstamp.format(now) + "\n");
        sb.append("TODAY=" + today.format(now) + "\n");
        sb.append("TSTAMP=" + tstamp.format(now) + "\n");
        properties.remove("DSTAMP");
        properties.remove("TODAY");
        properties.remove("TSTAMP");
        writeProperties(file, sb.toString(), properties);
    }

    protected void writeProperties(File file, String comment, Properties properties) throws MojoExecutionException {
        List<String> names = new ArrayList<String>(properties.stringPropertyNames());
        Collections.sort(names);
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isBlank(comment)) {
            sb.append(comment);
        }
        for (String name : names) {
            String value = properties.getProperty(name);
            String escapedValue = escape(value, ESCAPE_CHARS);
            sb.append(name + "=" + escapedValue + "\n");
        }
        try {
            FileUtils.writeStringToFile(file, sb.toString());
        } catch (IOException e) {
            throw new MojoExecutionException("Error creating properties file", e);
        }
    }

    protected String escape(String s, String[] escapeChars) {
        for (String escapeChar : escapeChars) {
            s = s.replace(escapeChar, "\\" + escapeChar);
        }
        return s;

    }

    public boolean isAntEchoPropertiesMode() {
        return antEchoPropertiesMode;
    }

    public void setAntEchoPropertiesMode(boolean antEchoPropertiesMode) {
        this.antEchoPropertiesMode = antEchoPropertiesMode;
    }

    public boolean isIncludeSystemProperties() {
        return includeSystemProperties;
    }

    public void setIncludeSystemProperties(boolean includeSystemProperties) {
        this.includeSystemProperties = includeSystemProperties;
    }
}
