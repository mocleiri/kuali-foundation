/**
 * Copyright 2011-2012 The Kuali Foundation
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
package org.kuali.maven.plugins.ingester;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.kew.batch.MyXMLPollerServiceImpl;
import org.kuali.rice.kew.doctype.bo.DocumentType;
import org.kuali.rice.kew.export.KewExportDataSet;
import org.kuali.rice.kew.service.KEWServiceLocator;
import java.util.Iterator;

import org.apache.log4j.Logger;

/**
 * Export workflow documents into Rice
 * 
 * @goal export
 */
public class ExportMojo extends AbstractMojo {

	
    private static final Logger LOG = Logger.getLogger(ExportMojo.class);

	
    /**
     * The Maven project object
     * 
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     * The type of database (mysql, oracle, etc)
     * 
     * @parameter expression="${ingester.jdbcVendor}" default-value="mysql"
     * @required
     */
    private String jdbcVendor;

    /**
     * The jdbc connect url
     * 
     * @parameter expression="${ingester.jdbcUrl}"
     * @required
     */
    private String jdbcUrl;

    /**
     * The database username
     * 
     * @parameter expression="${ingester.jdbcUsername}"
     * @required
     */
    private String jdbcUsername;

    /**
     * The database password for the specified username
     * 
     * @parameter expression="${ingester.jdbcPassword}"
     * @required
     */
    private String jdbcPassword;

    /**
     * Jdbc driver to use. This is optional because a default driver for the specified <code>jdbcVendor</code> is
     * usually the correct one to use. If a driver is provided here, it overrides the default.
     * 
     * @parameter expression="${ingester.jdbcDriver}"
     */
    private String jdbcDriver;

    /**
     * Namespace for Rice
     * 
     * @parameter expression="${ingester.namespace}"
     * @required
     */
    private String namespace;

    /**
     * The directory containing documents to ingest
     * 
     * @parameter expression="${ingester.sourceDir}" default-value="${project.basedir}/src/main/resources"
     * @required
     */
    private File sourceDir;

    /**
     * The working directory where the documents to ingest are copied. The plugin loads them from here, and transitions
     * them from the "pending" directory to either the "completed" or "problem" directories.
     * 
     * @parameter expression="${ingester.workingDir}" default-value="${project.build.directory}/ingester"
     * @required
     */
    private File workingDir;

    /**
     * System property key the plugin uses to locate an external properties file to load
     * 
     * @parameter expression="${ingester.propsKey}" default-value="ingester.config.location"
     */
    private String propsKey;

    /**
     * Location of the external properties file to load
     * 
     * @parameter expression="${ingester.propsLoc}"
     *            default-value="${project.build.directory}/ingester/config/ingester.properties"
     */
    private File propsLoc;

    /**
     * Inclusion patterns. By default &#42;&#42;/&#42;.xml is included
     * 
     * @parameter
     */
    private String[] includes = { "**/*.xml" };

    /**
     * Exclusion patterns. By default, nothing is excluded.
     * 
     * @parameter
     */
    private String[] excludes;

    @Override
    public void execute() throws MojoExecutionException {
        showConfig();
        prepareFileSystem();
        prepareProperties();
        try {
			exportKewDocumentTypeRepository();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
    }

	protected void prepareFileSystem() throws MojoExecutionException {
		try {
			if (getBaseDir().exists()) {
				FileUtils.cleanDirectory(getBaseDir());
			} else {
				FileUtils.forceMkdir(getBaseDir());
			}
			FileUtils.forceMkdir(getDocTypeDir());

		} catch (IOException e) {
			throw new MojoExecutionException(
					"Error preparing directory structure", e);
		}
	}

    protected void prepareProperties() throws MojoExecutionException {
        String value = System.getProperty(propsKey);
        if (!StringUtils.isBlank(value)) {
            // They have provided a location to pull properties from.
            // We will do nothing further. They are on their own to make sure the configuration is correct
            return;
        }

        String path = getPath(propsLoc);
        System.setProperty(propsKey, path);
        getLog().info("Setting " + propsKey + "=" + path);

        String jdbcVendorValue = System.getProperty("jdbc.vendor");
        if (StringUtils.isBlank(jdbcVendorValue)) {
            System.setProperty("jdbc.vendor", jdbcVendor);
        }

        Properties properties = new Properties();
        properties.setProperty("app.namespace", namespace);
        properties.setProperty("jdbc.url", jdbcUrl);
        properties.setProperty("jdbc.vendor", jdbcVendor);
        properties.setProperty("jdbc.username", jdbcUsername);
        properties.setProperty("jdbc.password", jdbcPassword);
        if (!StringUtils.isBlank(jdbcDriver)) {
            properties.setProperty("jdbc.driver", jdbcDriver);
        }
        try {
            getLog().info("Creating " + path);
            PropertyUtils.store(properties, propsLoc);
        } catch (IOException e) {
            throw new MojoExecutionException("Error storing properties", e);
        }
    }

    protected String getRelativePath(File dir, File file) {
        String dirPath = dir.getAbsolutePath();
        String filePath = file.getAbsolutePath();
        String s = StringUtils.replace(filePath, dirPath, "");
        return s.substring(1);
    }




    protected void showConfig() throws MojoExecutionException {
        try {
            getLog().info("Namespace - " + namespace);
            getLog().info("Directory - " + sourceDir.getCanonicalPath());
            getLog().info("JDBC Vendor - " + jdbcVendor);
            getLog().info("JDBC Url - " + jdbcUrl);
            getLog().info("JDBC Username - " + jdbcUsername);
        } catch (IOException e) {
            throw new MojoExecutionException("Error showing config", e);
        }
    }

    protected String getPath(File file) throws MojoExecutionException {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            throw new MojoExecutionException("Error obtaining canonical path", e);
        }
    }

    public String[] getIncludes() {
        return includes;
    }

    public void setIncludes(String[] includes) {
        this.includes = includes;
    }

    public String[] getExcludes() {
        return excludes;
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }

    public File getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(File sourceDir) {
        this.sourceDir = sourceDir;
    }

    public File getWorkingDir() {
        return workingDir != null ? workingDir : FileUtils.getTempDirectory();
    }
    
    public File getBaseDir() {
    	return new File(getWorkingDir(), "repository");
    }
    
    public File getDocTypeDir() {
    	return new File(getBaseDir(), "docType");
    }

    public void setWorkingDir(File outputDir) {
        this.workingDir = outputDir;
    }

    public MavenProject getProject() {
        return project;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getPropsKey() {
        return propsKey;
    }

    public void setPropsKey(String propsKey) {
        this.propsKey = propsKey;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcVendor() {
        return jdbcVendor;
    }

    public void setJdbcVendor(String jdbcVendor) {
        this.jdbcVendor = jdbcVendor;
    }
    
    protected void exportKewDocumentTypeRepository() throws Exception {
    	SpringContext.initializeApplicationContext();
        List documentTypes = KEWServiceLocator.getDocumentTypeService().findAllCurrent();
        HashMap<String, String> paramMap = new HashMap<String, String>();

        // Iterate through current DocumentTypes
        for (Iterator iterator = documentTypes.iterator(); iterator.hasNext();) {
            DocumentType existingDocType = (DocumentType) iterator.next();
            LOG.info("Loading " +  existingDocType.getName() + " (" +  existingDocType.getDocumentId());
            
            // Setup directory path and temp file
            File currentDir = getDocTypeDir();
            if(getPath(existingDocType) != "") {
                currentDir =  new File(getDocTypeDir(), getPath(existingDocType));
                FileUtils.forceMkdir(currentDir);
            }
            File docTypeXmlFile = new File(currentDir, existingDocType.getName() + ".xml");
            
            // Reused code
            KewExportDataSet dataSet = new KewExportDataSet();
            dataSet.getDocumentTypes().add(existingDocType);
            byte[] xmlBytes = CoreApiServiceLocator.getXmlExporterService().export(dataSet.createExportDataSet());
            
            // Output to temp files
            FileOutputStream out = new FileOutputStream(docTypeXmlFile);
            out.write(xmlBytes);
            out.close();

            // Generate md5Hex and resource path for tracking
            FileInputStream fis = new FileInputStream(docTypeXmlFile);
            String hashID = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);
            LOG.info("wrote " + docTypeXmlFile.getAbsolutePath() + " - md5Hash: " + hashID);
            paramMap.put(existingDocType.getName() + ".md5", hashID);
            paramMap.put(existingDocType.getName() + ".resource", getPath(existingDocType) + docTypeXmlFile.getName());
        }
        writeDocTypeConfigXmlFile(paramMap);
    }

    private String getPath(DocumentType documentType) {
      String path = "";
      DocumentType currentDocumentType = documentType;
      while(currentDocumentType.getParentDocType() != null) {
          path = currentDocumentType.getParentDocType().getName() + "/" + path;
          currentDocumentType = currentDocumentType.getParentDocType();
      }
      return path;
    }
    
    private void writeDocTypeConfigXmlFile(HashMap<String, String> map) throws IOException {
        File configXmlFile = new File(getBaseDir(), "docType-config.xml");
        FileWriter writer = new FileWriter(configXmlFile);
        writer.write("<config>\n");
        for(String key: new TreeSet<String>(map.keySet()) ) {
            String param = "\t<param name=\"" + key + "\" value =\"" + map.get(key) + "\" />\n";
            writer.write(param);
        }
        writer.write("</config>\n");
        writer.close();
    }

}
