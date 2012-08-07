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
import java.util.Arrays;
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
 * Write project properties to a file.
 * 
 * @author Jeff Caddel
 * 
 * @goal write-project-properties
 */
public class WriteProjectProperties extends AbstractWritePropertiesMojo {
    private static final String CR = "\r";
    private static final String LF = "\n";
    private static final String TAB = "\t";
    private static final String[] ANT_ESCAPE_CHARS = { CR, LF, TAB, ":", "#", "=" };

    /**
     * Comma separated list of characters to escape when writing property values. cr=carriage return, lf=linefeed,
     * tab=tab. Any other values are taken literally.
     * 
     * @parameter default-value="cr,lf,tab" expression="${properties.escapeChars}"
     */
    private String escapeChars;

    /**
     * If true, the plugin will create the properties file formatted the same way Ant formats properties files using the
     * <code>echoproperties</code> task. This mode adds 3 custom properties at the top of the file, DSTAMP, TODAY, and
     * TSTAMP. In this mode <code>escapeChars</code> is ignored and the 6 characters Ant escapes are used instead
     * <code>CR</code>,<code>LF</code>,<code>TAB</code>,<code>:</code>,<code>#</code>,<code>=</code>
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
            // Add environment variables, overriding any existing properties with the same key
            properties.putAll(getEnvironmentVariables());
        }
        if (includeSystemProperties) {
            // Add system properties, overriding any existing properties with the same key
            properties.putAll(System.getProperties());
        }

        // Remove properties as appropriate
        trim(properties, exclude, include);

        String comment = "# " + new Date() + "\n";
        List<String> escapeTokens = getEscapeChars(escapeChars);
        if (antEchoPropertiesMode) {
            escapeTokens = Arrays.asList(ANT_ESCAPE_CHARS);
            comment = getAntHeader();
            properties.remove("DSTAMP");
            properties.remove("TODAY");
            properties.remove("TSTAMP");
        }

        getLog().info("Creating " + outputFile);
        writeProperties(outputFile, comment, properties, escapeTokens);
    }

    protected static Properties getEnvironmentVariables() {
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

    protected String getAntHeader() {
        SimpleDateFormat dstamp = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat today = new SimpleDateFormat("MMMM d yyyy");
        SimpleDateFormat tstamp = new SimpleDateFormat("HHmm");
        Date now = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("# Ant properties\n");
        sb.append("# " + now + "\n");
        sb.append("DSTAMP=" + dstamp.format(now) + "\n");
        sb.append("TODAY=" + today.format(now) + "\n");
        sb.append("TSTAMP=" + tstamp.format(now) + "\n");
        return sb.toString();
    }

    protected List<String> getEscapeChars(String escapeChars) {
        List<String> tokens = ReadPropertiesMojo.getListFromCSV(escapeChars);
        List<String> realTokens = new ArrayList<String>();
        for (String token : tokens) {
            String realToken = getRealToken(token);
            realTokens.add(realToken);
        }
        return realTokens;
    }

    protected String getRealToken(String token) {
        if (token.equalsIgnoreCase("CR")) {
            return CR;
        } else if (token.equalsIgnoreCase("LF")) {
            return LF;
        } else if (token.equalsIgnoreCase("TAB")) {
            return TAB;
        } else {
            return token;
        }
    }

    protected void writeProperties(File file, String comment, Properties properties, List<String> escapeTokens)
            throws MojoExecutionException {
        List<String> names = new ArrayList<String>(properties.stringPropertyNames());
        Collections.sort(names);
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isBlank(comment)) {
            sb.append(comment);
        }
        for (String name : names) {
            String value = properties.getProperty(name);
            String escapedValue = escape(value, escapeTokens);
            sb.append(name + "=" + escapedValue + "\n");
        }
        try {
            FileUtils.writeStringToFile(file, sb.toString());
        } catch (IOException e) {
            throw new MojoExecutionException("Error creating properties file", e);
        }
    }

    protected String escape(String s, List<String> escapeChars) {
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

    public String getEscapeChars() {
        return escapeChars;
    }

    public void setEscapeChars(String escapeChars) {
        this.escapeChars = escapeChars;
    }

    public boolean isIncludeEnvironmentVariables() {
        return includeEnvironmentVariables;
    }

    public void setIncludeEnvironmentVariables(boolean includeEnvironmentVariables) {
        this.includeEnvironmentVariables = includeEnvironmentVariables;
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }
}
