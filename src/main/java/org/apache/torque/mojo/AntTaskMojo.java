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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.torque.util.MojoAntBuildListener;
import org.kuali.core.db.torque.FilteredPropertyCopier;

/**
 * A base class for mojos that wrap an Ant Task
 */
public class AntTaskMojo extends BaseMojo {

	/**
	 * The ant task to be executed by this mojo.
	 */
	private Task antTask;

	/**
	 * The ant project for the ant task.
	 */
	private Project antProject;

	/**
	 * Configures the Ant task which is wrapped by this mojo.
	 */
	protected void configureTask() throws MojoExecutionException {
		if (getAntTask() == null) {
			throw new IllegalArgumentException("Ant task is null");
		}

		// Attach our task to a project
		setAntProject(getIniatializedAntProject());
		getAntTask().setProject(getAntProject());
		try {
			// Copy configuration from the mojo to the task
			FilteredPropertyCopier copier = new FilteredPropertyCopier();
			// There is a setProject() method on an Ant Task that expects an Ant Project. This conflicts with
			// getProject() from the mojo which returns a Maven Project
            copier.addExclude("project");
            copier.addExclude("driverProperties");
			copier.copyProperties(getAntTask(), this);
		} catch (Exception e) {
			throw new MojoExecutionException("Error copying properties", e);
		}
	}

	/**
	 * Configure the Ant task and then execute it
	 */
	@Override
    public void executeMojo() throws MojoExecutionException {
		configureTask();
		getAntTask().execute();
	}

	/**
	 * Return an Ant project that informs Maven about logging events
	 */
	protected Project getIniatializedAntProject() {
		getLog().info("Initializing the Ant Project");
		// Create a new Ant Project
		Project antProject = new Project();
		// initialize it
		antProject.init();
		// Add a listener that gets notified about log messages
		antProject.addBuildListener(new MojoAntBuildListener(getLog()));
		// Return the initialized ant project
		return antProject;
	}

	public Project getAntProject() {
		return antProject;
	}

	public void setAntProject(Project antProject) {
		this.antProject = antProject;
	}

	public Task getAntTask() {
		return antTask;
	}

	public void setAntTask(Task antTask) {
		this.antTask = antTask;
	}
}
