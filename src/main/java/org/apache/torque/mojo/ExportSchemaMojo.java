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
package org.apache.torque.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.kuali.core.db.torque.KualiTorqueSchemaDumpTask;

/**
 * Examines the JDBC metadata of a proprietary database and exports the schema information to database agnostic XML. The
 * generated XML contains information about tables, primary keys, unique constraints, foreign keys, indexes, sequences,
 * and views. This goal does not export any data. See also <code>impex:exportdata</code> and <code>impex:import</code>
 * 
 * @goal exportschema
 * @phase generate-sources
 */
public class ExportSchemaMojo extends ExportMojo {

	/**
	 * Flag indicating whether or not tables will be processed. Default is true
	 * 
	 * @parameter expression="${processTables}" default-value="true"
	 */
	private boolean processTables;

	/**
	 * Flag indicating whether or not views will be processed. Default is true
	 * 
	 * @parameter expression="${processViews}" default-value="true"
	 */
	private boolean processViews;

	/**
	 * Flag indicating whether or not sequences will be processed. Default is true
	 * 
	 * @parameter expression="${processSequences}" default-value="true"
	 */
	private boolean processSequences;

	/**
	 * The file that the schema XML will get written to
	 * 
	 * @parameter expression="${schemaXMLFile}" default-value="${basedir}/src/main/impex/${project.artifactId}.xml"
	 * @required
	 */
	private String schemaXMLFile;

	/**
	 * Configure the Ant task
	 */
	protected void configureTask() throws MojoExecutionException {

		KualiTorqueSchemaDumpTask task = new KualiTorqueSchemaDumpTask();
		setAntTask(task);
		super.configureTask();
	}

	public boolean isProcessTables() {
		return processTables;
	}

	public void setProcessTables(boolean processTables) {
		this.processTables = processTables;
	}

	public boolean isProcessViews() {
		return processViews;
	}

	public void setProcessViews(boolean processViews) {
		this.processViews = processViews;
	}

	public boolean isProcessSequences() {
		return processSequences;
	}

	public void setProcessSequences(boolean processSequences) {
		this.processSequences = processSequences;
	}

	public String getSchemaXMLFile() {
		return schemaXMLFile;
	}

	public void setSchemaXMLFile(String schemaXMLFile) {
		this.schemaXMLFile = schemaXMLFile;
	}

}
