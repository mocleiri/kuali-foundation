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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.texen.Generator;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.types.FileSet;
import org.apache.torque.engine.EngineException;
import org.apache.torque.engine.database.model.Database;
import org.apache.torque.engine.database.model.Table;
import org.apache.torque.engine.database.transform.XmlToData;
import org.apache.torque.task.TorqueDataModelTask;
import org.apache.torque.task.TorqueDataSQLTask;
import org.apache.torque.task.TorqueDataModelTask.TorqueClasspathResourceLoader;
import org.apache.torque.task.TorqueDataModelTask.TorqueFileResourceLoader;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.xml.sax.SAXException;

/**
 * 
 * @author $Author: lprzybyl $
 * @version $Revision: 1.1.2.2 $
 */
public class KualiTorqueDataSqlTask extends TorqueDataModelTask {
    private static Map<String, String> invalidSubStringMap = new HashMap<String, String>();
    static {
        invalidSubStringMap.put("_DOLLAR_SIGN_", "$");
    }

    /** the XML data file */
    private String dataXmlFile;
    /** the data dtd file */
    private String dataDTD;


    /**
     * Sets the DataXmlFile attribute of the TorqueDataSQLTask object
     * 
     * @param dataXmlFile The new DataXmlFile value
     */
    public void setDataXmlFile(String dataXmlFile) {
        this.dataXmlFile = getProject().resolveFile(dataXmlFile).toString();
    }

    /**
     * Gets the DataXmlFile attribute of the TorqueDataSQLTask object
     * 
     * @return The DataXmlFile value
     */
    public String getDataXmlFile() {
        return dataXmlFile;
    }

    /**
     * Gets the DataDTD attribute of the TorqueDataSQLTask object
     * 
     * @return The DataDTD value
     */
    public String getDataDTD() {
        return dataDTD;
    }

    /**
     * Sets the DataDTD attribute of the TorqueDataSQLTask object
     * 
     * @param dataDTD The new DataDTD value
     */
    public void setDataDTD(String dataDTD) {
        this.dataDTD = getProject().resolveFile(dataDTD).toString();
    }

    /**
     * This message fragment (telling users to consult the log or invoke ant with the -debug flag) is appended to rethrown exception
     * messages.
     */
    private final static String ERR_MSG_FRAGMENT = ". For more information consult the velocity log, or invoke ant "
            + "with the -debug flag.";


    public Context initControlContext(String dataXmlFile, String outputFile) throws Exception {
        Context retval = new VelocityContext(context);

        Database db = (Database) getDataModels().get(0);
        List data = new ArrayList();
        
        KualiXmlToData dataXmlParser = new KualiXmlToData(db, getDataDTD());
        data = dataXmlParser.parseFile(dataXmlFile);
        
        for (Object obj : data) {
        	KualiXmlToData.DataRow row = (KualiXmlToData.DataRow) obj;
        	substituteValidTableName(row.getTable());
        }
       
        retval.put("data", data);

        context.put("outputFile", outputFile);
        Properties p = new Properties();
        FileInputStream fis = new FileInputStream(getSqlDbMap());
        p.load(fis);
        fis.close();

        p.setProperty(getOutputFile(), db.getName());
        p.store(new FileOutputStream(getSqlDbMap()), "Sqlfile -> Database map");

        return retval;
    }
    
    private void substituteValidTableName(Table table) {
        for (Entry<String, String> entry: invalidSubStringMap.entrySet()) {
            if (table.getName().indexOf(entry.getKey()) > -1) {
                log("Found name substition for " + table.getName());
                table.setName(StringUtils.replace(table.getName(), entry.getKey(), entry.getValue()));
                log("New name is " + table.getName());
            }
        }    	
    }
    
    /**
     * Set up the initial context for generating the SQL from the XML schema.
     * 
     * @return the context
     * @throws Exception If there is an error parsing the data xml.
     */
    public Context initControlContext() throws Exception {
        super.initControlContext();

        Database db = (Database)

        // Place our model in the context.
        context.put("appData", getDataModels().get(0));
        
        context.put("task", this);

        context.remove("dataModels");

        // Place the target database in the context.
        context.put("targetDatabase", getTargetDatabase());

        return context;
    }

    private void preExec() throws BuildException {
        // Make sure the template path is set.
        if (templatePath == null && useClasspath == false) {
            throw new BuildException("The template path needs to be defined if you are not using "
                    + "the classpath for locating templates!");
        }

        // Make sure the control template is set.
        if (controlTemplate == null) {
            throw new BuildException("The control template needs to be defined!");
        }

        // Make sure the output directory is set.
        if (outputDirectory == null) {
            throw new BuildException("The output directory needs to be defined!");
        }

        if (filesets.isEmpty()) {
            throw new BuildException("You must specify a fileset of XML data files!");
        }
    }

    private Properties getGeneratorDefaultProperties() {
        Properties props = new Properties();
        ClassLoader classLoader = VelocityEngine.class.getClassLoader();
        try {
            InputStream inputStream = null;
            try {
                inputStream = classLoader.getResourceAsStream("org/apache/texen/defaults/texen.properties");

                props.load(inputStream);
            }
            finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
        catch (IOException ioe) {
            System.err.println("Cannot get default properties: " + ioe.getMessage());
        }

        return props;
    }

    private KualiGenerator buildGenerator() throws Exception {
        VelocityEngine ve = new VelocityEngine();
        KualiGenerator generator = KualiGenerator.getInstance();

        // Setup the Velocity Runtime.
        if (templatePath != null) {
            log("Using templatePath: " + templatePath, project.MSG_VERBOSE);
            ve.setProperty("torque" + VelocityEngine.FILE_RESOURCE_LOADER_PATH, templatePath);
            // TR: We need our own FileResourceLoader
            ve.addProperty(VelocityEngine.RESOURCE_LOADER, "torquefile");
            ve.setProperty("torquefile." + VelocityEngine.RESOURCE_LOADER + ".instance", new TorqueFileResourceLoader(this));
            generator.setTemplatePath(templatePath);
        }

        if (useClasspath) {
            log("Using classpath");
            // TR: We need our own ClasspathResourceLoader
            ve.addProperty(VelocityEngine.RESOURCE_LOADER, "classpath");

            ve.setProperty("classpath." + VelocityEngine.RESOURCE_LOADER + ".instance", new TorqueClasspathResourceLoader(this));

            ve.setProperty("classpath." + VelocityEngine.RESOURCE_LOADER + ".cache", "false");

            ve.setProperty("classpath." + VelocityEngine.RESOURCE_LOADER + ".modificationCheckInterval", "2");
        }

        ve.init();

        generator.setVelocityEngine(ve);
        generator.setOutputPath(outputDirectory);
        generator.setInputEncoding(inputEncoding);
        generator.setOutputEncoding(outputEncoding);

        return generator;
    }

    /**
     * This method creates an VelocityEngine instance, parses every template and creates the corresponding output.
     * 
     * Unfortunately the TextenTask.execute() method makes everything for us but we just want to set our own VelocityTemplateLoader.
     * TODO: change once TEXEN-14 is resolved and out.
     * 
     * @see org.apache.texen.ant.TexenTask#execute()
     */
    public void execute() throws BuildException {
        KualiGenerator generator;
        preExec();

        try {
            initControlContext();
            generator = buildGenerator();

            // Everything in the generator class should be
            // pulled out and placed in here. What the generator
            // class does can probably be added to the Velocity
            // class and the generator class can probably
            // be removed all together.
            populateInitialContext(context);
        }
        catch (Exception e) {
            throw new BuildException(e);
        }

        for (FileSet fs : (List<FileSet>) filesets) {
            DirectoryScanner ds = fs.getDirectoryScanner(getProject());
            File srcDir = fs.getDir(getProject());

            String[] dataModelFiles = ds.getIncludedFiles();

            // Make a transaction for each file
            for (String inputFile : dataModelFiles) {
                outputFile = inputFile.substring(0, inputFile.length() - 4) + ".sql";
                inputFile = srcDir.getAbsolutePath() + File.separator + inputFile;

                try {
                    // The generator and the output path should
                    // be placed in the init context here and
                    // not in the generator class itself.
                    Context c = initControlContext(inputFile, outputFile);

                    // Make sure the output directory exists, if it doesn't
                    // then create it.
                    File file = new File(outputDirectory);
                    if (!file.exists()) {
                        file.mkdirs();
                    }

                    log("Generating to file " + outputFile, project.MSG_INFO);

                    generator.parse(controlTemplate, c);
                    // processOutput(generator, c, path);

                    generator.shutdown();
                    cleanup();
                }
                catch (BuildException e) {
                    throw e;
                }
                catch (MethodInvocationException e) {
                    throw new BuildException("Exception thrown by '" + e.getReferenceName() + "." + e.getMethodName() + "'"
                            + ERR_MSG_FRAGMENT, e.getWrappedThrowable());
                }
                catch (ParseErrorException e) {
                    throw new BuildException("Velocity syntax error" + ERR_MSG_FRAGMENT, e);
                }
                catch (ResourceNotFoundException e) {
                    throw new BuildException("Resource not found" + ERR_MSG_FRAGMENT, e);
                }
                catch (Exception e) {
                    throw new BuildException("Generation failed" + ERR_MSG_FRAGMENT, e);
                }
            }
        }
    }

    private void processOutput(Generator generator, Context c, String path) throws Exception {
        log("Generating to file " + path, project.MSG_INFO);
        Writer writer = generator.getWriter(path, outputEncoding);
        writer.write(generator.parse(controlTemplate, c));
        writer.flush();
        writer.close();
    }

    private void populateControlContext(Context c) throws Exception {
        // Feed all the options into the initial
        // control context so they are available
        // in the control/worker templates.
        if (contextProperties != null) {
            Iterator i = contextProperties.getKeys();

            while (i.hasNext()) {
                String property = (String) i.next();
                String value = contextProperties.getString(property);

                // Now lets quickly check to see if what
                // we have is numeric and try to put it
                // into the context as an Integer.
                try {
                    c.put(property, new Integer(value));
                }
                catch (NumberFormatException nfe) {
                    // Now we will try to place the value into
                    // the context as a boolean value if it
                    // maps to a valid boolean value.
                    String booleanString = contextProperties.testBoolean(value);

                    if (booleanString != null) {
                        c.put(property, new Boolean(booleanString));
                    }
                    else {
                        // We are going to do something special
                        // for properties that have a "file.contents"
                        // suffix: for these properties will pull
                        // in the contents of the file and make
                        // them available in the context. So for
                        // a line like the following in a properties file:
                        //
                        // license.file.contents = license.txt
                        //
                        // We will pull in the contents of license.txt
                        // and make it available in the context as
                        // $license. This should make texen a little
                        // more flexible.
                        if (property.endsWith("file.contents")) {
                            // We need to turn the license file from
                            // relative to
                            // absolute, and let Ant help :)
                            value = org.apache.velocity.util.StringUtils.fileContentsToString(getProject().resolveFile(value)
                                    .getCanonicalPath());

                            property = property.substring(0, property.indexOf("file.contents") - 1);
                        }

                        c.put(property, value);
                    }
                }
            }
        }
    }
}
