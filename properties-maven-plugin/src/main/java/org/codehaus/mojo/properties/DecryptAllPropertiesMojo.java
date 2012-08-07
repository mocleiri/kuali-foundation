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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 * Inspect project and system properties for any keys ending with <code>endsWith</code>. Any matching properties are
 * assumed to be encrypted. They are decrypted and stored as project properties minus the <code>endsWith</code> suffix.
 * For example, the value for the property "dba.password.encrypted" will be decrypted and stored as "dba.password"
 * 
 * @goal decryptall
 */
public class DecryptAllPropertiesMojo extends AbstractMojo {

    /**
     * @parameter default-value="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

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
     * If true, the plugin will emit no logging information
     * 
     * @parameter expression="${properties.quiet}" default-value="false"
     * @required
     */
    private boolean quiet;

    /**
     * The pattern for matching properties in need of decryption
     * 
     * @parameter expression="${properties.endsWith}" default-value=".encrypted"
     * @required
     */
    private String endsWith;

    /**
     * If true the plain text decrypted values are displayed to the console.
     * 
     * @parameter expression="${properties.show}" default-value="false"
     * @required
     */
    private boolean show;

    /**
     * The password for decrypting property values. This same password must have been used to encrypt them.
     * 
     * @parameter expression="${properties.password}"
     * @required
     */
    private String password;

    @Override
    public void execute() throws MojoExecutionException {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(password);
        Properties props = new Properties();
        props.putAll(project.getProperties());
        if (includeEnvironmentVariables) {
            props.putAll(WriteProjectProperties.getEnvironmentVariables());
        }
        if (includeSystemProperties) {
            props.putAll(System.getProperties());
        }
        List<String> keys = new ArrayList<String>(props.stringPropertyNames());
        Collections.sort(keys);
        for (String key : keys) {
            boolean decrypt = key.endsWith(endsWith);
            if (!decrypt) {
                continue;
            }
            String value = getProperty(key);
            if (StringUtils.isBlank(value) && !quiet) {
                getLog().info("Skipping blank property " + key);
                continue;
            }
            String newValue = encryptor.decrypt(value);
            int length = endsWith.length();
            String newKey = key.substring(0, key.length() - length);
            project.getProperties().setProperty(newKey, newValue);
            if (quiet) {
                continue;
            }
            if (show) {
                getLog().info("Setting " + newKey + "=" + newValue + " - " + value);
            } else {
                getLog().info("Setting " + newKey);
            }
        }
    }

    protected String getProperty(String key) {
        String sys = System.getProperty(key);
        String proj = project.getProperties().getProperty(key);
        if (!StringUtils.isBlank(sys)) {
            return sys;
        } else {
            return proj;
        }
    }

    public boolean isQuiet() {
        return quiet;
    }

    public void setQuiet(boolean quiet) {
        this.quiet = quiet;
    }

    public String getEndsWith() {
        return endsWith;
    }

    public void setEndsWith(String endsWith) {
        this.endsWith = endsWith;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MavenProject getProject() {
        return project;
    }
}
