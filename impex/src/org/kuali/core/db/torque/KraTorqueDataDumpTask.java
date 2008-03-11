/*
 * Copyright 2006-2007 The Kuali Foundation.
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
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.torque.engine.database.model.Database;
import org.apache.torque.engine.database.model.Table;
import org.apache.torque.engine.database.transform.XmlToAppData;
import org.apache.torque.task.TorqueDataDumpTask;
import org.apache.velocity.context.Context;

public class KraTorqueDataDumpTask extends Task {

	/** Database URL used for JDBC connection. */
	private String databaseUrl;

	/** Database driver used for JDBC connection. */
	private String databaseDriver;

	/** Database user used for JDBC connection. */
	private String databaseUser;
	
	/** Database password used for JDBC connection. */
	private String databasePassword;


	/**
	 * Get the database url
	 * 
	 * @return The DatabaseUrl value
	 */
	public String getDatabaseUrl() {
		return databaseUrl;
	}

	/**
	 * Set the database url
	 * 
	 * @param v
	 *            The new DatabaseUrl value
	 */
	public void setDatabaseUrl(String v) {
		databaseUrl = v;
	}

	/**
	 * Get the database driver name
	 * 
	 * @return String database driver name
	 */
	public String getDatabaseDriver() {
		return databaseDriver;
	}

	/**
	 * Set the database driver name
	 * 
	 * @param v
	 *            The new DatabaseDriver value
	 */
	public void setDatabaseDriver(String v) {
		databaseDriver = v;
	}

	/**
	 * Get the database user
	 * 
	 * @return String database user
	 */
	public String getDatabaseUser() {
		return databaseUser;
	}

	/**
	 * Set the database user
	 * 
	 * @param v
	 *            The new DatabaseUser value
	 */
	public void setDatabaseUser(String v) {
		databaseUser = v;
	}

	/**
	 * Get the database password
	 * 
	 * @return String database password
	 */
	public String getDatabasePassword() {
		return databasePassword;
	}

	/**
	 * Set the database password
	 * 
	 * @param v
	 *            The new DatabasePassword value
	 */
	public void setDatabasePassword(String v) {
		databasePassword = v;
	}
	
	/**
	 * Initializes initial context
	 * 
	 * @return the context
	 * @throws Exception
	 *             generic exception
	 */
	public void execute() throws BuildException {
	    TorqueDataDumpTask torqueTask = null;
	   
	    for (String tableName : getTableNames()) {
	        log("Processing table " + tableName);
	        torqueTask = buildTorqueTask(tableName);
	        torqueTask.execute();
	    }
	}
	
	/**
	 * Delegates to the <code>{@link TorqueDataDumpTask}</code>. Normally, <code>{@link TorqueDataDump}</code> will dump the data
	 * for all of the tables. This is not what we want because parsing will consume a lot of memory. Instead, we want each table to 
	 * represent a dataset. This way, <code>{@link TorqueDataDumpTask}</code> will produce a new file for each table. Plus,
	 * normally <code>{@link TorqueDataDumpTask}</code> 
	 * 
	 * @return TorqueDataDumpTask
	 * @throws BuildException
	 */
	private TorqueDataDumpTask buildTorqueTask(String tableName) throws BuildException {
	    TorqueDataDumpTask retval = new TorqueDataDumpTask() {
	        public Context initControlContext() throws Exception {
	            super.initControlContext();
	            
	            String dataset = (String) getContextProperties().getProperty("torque.current.dataset");
	            log("Setting current dataset to: " + dataset);
	            context.put("dataset", dataset);
	            
	            return context;
	        }
	    };
	    retval.setProject(getProject());
	    retval.setTaskName(getTaskName());
	    retval.setContextProperties(getProject().getProperty("torque.contextProperties"));
        retval.setControlTemplate(getProject().getProperty("torque.template.dataDump"));
        retval.setDatabaseName(getProject().getProperty("torque.database.name"));
        retval.setOutputDirectory(new File(getProject().getProperty("torque.output.dir")));
        try {
            retval.setTemplatePath(getProject().getProperty("torque.templatePath"));
        }
        catch (Exception e) {
            throw new BuildException(e);
        }
        retval.setUseClasspath(new Boolean(getProject().getProperty("torque.templatePath")).booleanValue());
        retval.setXmlFile(getXmlFile());
        retval.setOutputFile("report" + getProject().getProperty("torque.project") + "datadump.generation");
	    retval.setDatabaseDriver(getDatabaseDriver());
	    retval.setDatabaseUser(getDatabaseUser());
	    retval.setDatabasePassword(getDatabasePassword());
	    retval.setDatabaseUrl(getDatabaseUrl());
        retval.getContextProperties().setProperty("torque.current.dataset", tableName);
	    
	    return retval;
	}

	/**
	 * Create delegating <code>{@link TorqueDataDumpTask}</code>. If the task is already populated, it doesn't waste time
	 * repopulating. Just reuses the data. The way this is done is the existing <code>{@link TorqueDataDumpTask}</code> is passed
	 * in. It is only ever rebuilt if the reference is null.
	 * 
	 * @param dataDump
	 * @param tableName
	 * @return TorqueDataDumpTask
	 * @throws BuildException
	 * @see {@link #buildTorqueTask()}
	 */
    private TorqueDataDumpTask buildTorqueTask(TorqueDataDumpTask dataDump, String tableName) throws BuildException {
        TorqueDataDumpTask retval = dataDump;
        if (retval == null) {
            retval = buildTorqueTask(tableName);
        }
        
        retval.getContextProperties().setProperty("torque.current.dataset", tableName);
        
        return retval;
    }
    
    private String getXmlFile() {
        return getProject().getProperty("torque.schema.dir") + File.separator + getProject().getProperty("torque.project") + "-schema.xml";
    }
    
	/**
	 * Get all the table names in the current database that are not system
	 * tables.
	 * 
	 * @return The list of all the tables in a database.
	 */
	public List<String> getTableNames() {
	    List<String> retval = new ArrayList<String>();
	    
	    try {
	        KualiXmlToAppData xmlParser = new KualiXmlToAppData(null, null);
	        Database ad = xmlParser.parseFile(getXmlFile());
	        
	        for (Object obj : ad.getTables()) {
	            Table tableObject = (Table) obj;
	            
	            retval.add(tableObject.getName());
	        }
	    }
	    catch (Exception e) {
	        throw new BuildException(e);
	    }
	    
	    
	    return retval;
	}
}
