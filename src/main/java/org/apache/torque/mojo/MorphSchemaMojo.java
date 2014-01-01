/**
 * Copyright 2004-2014 The Kuali Foundation
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

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.torque.mojo.morph.MorphRequest;
import org.apache.torque.mojo.morph.Morpher;
import org.apache.torque.mojo.morph.SchemaMorpher;

/**
 * Convert an Ant impex schema XML file into a maven-impex-plugin schema XML file
 *
 * @goal morphschema
 * @phase generate-sources
 */
public class MorphSchemaMojo extends AbstractMorphSingleMojo {

	/**
	 * The XML file describing the database schema (Maven style)
	 *
	 * @parameter expression="${impex.antSchemaName}" default-value= "kfs"
	 * @required
	 */
	private String antSchemaName;

	/**
	 * The XML file describing the database schema (Maven style)
	 *
	 * @parameter expression="${newSchemaXMLFile}" default-value= "${project.build.directory}/generated-impex/${project.artifactId}.xml"
	 * @required
	 */
	private File newSchemaXMLFile;

	/**
	 * The XML file describing the database schema (Ant style)
	 *
	 * @parameter expression="${oldSchemaXMLFile}" default-value="${basedir}/src/main/impex/schema.xml"
	 * @required
	 */
	private File oldSchemaXMLFile;

	@Override
	protected void beforeExecution() {
		setNewFile(newSchemaXMLFile);
		setOldFile(oldSchemaXMLFile);
	}

	@Override
	protected void executeMojo() throws MojoExecutionException {
		getLog().info("------------------------------------------------------------------------");
		getLog().info("Converting schema XML file");
		getLog().info("------------------------------------------------------------------------");
		super.executeMojo();
	}

	@Override
	protected Morpher getMorpher(final MorphRequest request, final String artifactId) {
		SchemaMorpher morpher = new SchemaMorpher(request, artifactId);
		morpher.setAntSchemaName(antSchemaName);
		morpher.setAntSchemaToken("name=\"" + antSchemaName + "\"");
		return morpher;
	}

	public File getNewSchemaXMLFile() {
		return newSchemaXMLFile;
	}

	public void setNewSchemaXMLFile(final File newSchemaXMLFile) {
		this.newSchemaXMLFile = newSchemaXMLFile;
	}

	public File getOldSchemaXMLFile() {
		return oldSchemaXMLFile;
	}

	public void setOldSchemaXMLFile(final File oldSchemaXMLFile) {
		this.oldSchemaXMLFile = oldSchemaXMLFile;
	}

	public String getAntSchemaName() {
		return antSchemaName;
	}

	public void setAntSchemaName(String antSchemaName) {
		this.antSchemaName = antSchemaName;
	}
}
