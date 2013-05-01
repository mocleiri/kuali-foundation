/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.maven.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.project.MavenProject;
import org.springframework.util.PropertyPlaceholderHelper;

public class PropertiesUtils {
    PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
    ResourceUtils resourceUtils = new ResourceUtils();

    /**
     * Split the string trimming as we go
     */
    public static String[] splitAndTrim(String csv, String separator) {
        String[] tokens = StringUtils.split(csv, separator);
        for (String token : tokens) {
            token = token.trim();
        }
        return tokens;
    }

    public String getResolvedValue(String value, Properties properties) {
        return helper.replacePlaceholders(value, properties);
    }

    public Properties getProperties(List<String> locations) throws IOException {
        Properties properties = new Properties();
        for (String location : locations) {
            properties.putAll(getProperties(location));
        }
        return properties;
    }

    /**
     * Return properties as viewed by Maven. ie, project properties get overridden by system properties and environment
     * properties are prefixed with "env"
     */
    public Properties getMavenProperties(MavenProject project) {
        Properties properties = new Properties();
        properties.putAll(project.getProperties());
        properties.putAll(getEnvironmentProperties());
        properties.putAll(System.getProperties());
        return properties;
    }

    public Properties getEnvironmentProperties() {
        String prefix = "env.";
        Map<String, String> env = System.getenv();
        Properties properties = new Properties();
        for (Map.Entry<String, String> pair : env.entrySet()) {
            String key = prefix + pair.getKey();
            String value = pair.getValue();
            properties.setProperty(key, value);
        }
        return properties;
    }

    public Properties getProperties(String location) throws IOException {
        InputStream in = null;
        try {
            in = resourceUtils.getInputStream(location);
            Properties properties = new Properties();
            if (isXml(location)) {
                properties.loadFromXML(in);
            } else {
                properties.load(in);
            }
            return properties;
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public boolean isXml(String location) {
        return location.toLowerCase().endsWith(".xml");
    }
}
