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

package org.kuali.common.util.execute;

import org.kuali.common.util.ComparisonResults;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.SimpleScanner;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Properties;

/**
 * This executable compares the files found by the given pattern in two different locations and populates a properties object with
 * information about the comparison
 *
 * @author andrewlubbers
 */
public class FileListComparisonExecutable implements Executable {


    private String newFilesBaseDir;
    private String filePattern;
    private String originalFilesBaseDir;
    private List<String> propertyNames;
    private Properties mavenProperties;

    @Override
    public void execute() {
        Assert.notNull(mavenProperties);
        Assert.notNull(propertyNames);
        Assert.notNull(newFilesBaseDir);
        Assert.notNull(originalFilesBaseDir);
        Assert.notNull(filePattern);

        String[] includesArray = new String[]{filePattern};

        SimpleScanner scanner = new SimpleScanner();
        scanner.setBasedir(newFilesBaseDir);
        scanner.setIncludes(includesArray);

        List<String> newLocations = LocationUtils.getAbsolutePaths(scanner.getFiles());

        scanner = new SimpleScanner();
        scanner.setBasedir(originalFilesBaseDir);
        scanner.setIncludes(includesArray);

        List<String> originalLocations = LocationUtils.getAbsolutePaths(scanner.getFiles());

        ComparisonResults comparison = LocationUtils.getLocationListComparison(newLocations, originalLocations);

        PropertyUtils.addListComparisonProperties(mavenProperties, comparison, propertyNames);
    }

    public String getFilePattern() {
        return filePattern;
    }

    public void setFilePattern(String filePattern) {
        this.filePattern = filePattern;
    }

    public Properties getMavenProperties() {
        return mavenProperties;
    }

    public void setMavenProperties(Properties mavenProperties) {
        this.mavenProperties = mavenProperties;
    }

    public String getNewFilesBaseDir() {
        return newFilesBaseDir;
    }

    public void setNewFilesBaseDir(String newFilesBaseDir) {
        this.newFilesBaseDir = newFilesBaseDir;
    }

    public String getOriginalFilesBaseDir() {
        return originalFilesBaseDir;
    }

    public void setOriginalFilesBaseDir(String originalFilesBaseDir) {
        this.originalFilesBaseDir = originalFilesBaseDir;
    }

    public List<String> getPropertyNames() {
        return propertyNames;
    }

    public void setPropertyNames(List<String> propertyNames) {
        this.propertyNames = propertyNames;
    }
}
