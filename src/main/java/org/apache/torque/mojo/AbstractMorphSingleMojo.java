/**
 * Copyright 2004-2013 The Kuali Foundation
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.torque.mojo.morph.MorphRequest;
import org.apache.torque.mojo.morph.Morpher;
import org.codehaus.plexus.util.FileUtils;

/**
 * Common logic for morphing one file to another file
 */
public abstract class AbstractMorphSingleMojo extends BaseMojo {

	/**
	 * The artifactId (aka database schema)
	 *
	 * @parameter expression="${impex.artifactId}" default-value="${project.artifactId}"
	 * @required
	 */
	private String artifactId;

	/**
	 * The file that will contain the morphed contents
	 */
	private File newFile;

	/**
	 * The file containing the contents to be morphed
	 */
	private File oldFile;

	/**
	 * Add logic to the basic skip() method for skipping based on a morph being needed
	 */
	@Override
	protected boolean skipMojo() {
		// We may be skipping based on packaging of type 'pom'
		if (super.skipMojo()) {
			return true;
		}

		// If a morph is needed, we can't skip
		boolean morphNeeded = isMorphNeeded();
		if (morphNeeded) {
			return false;
		} else {
			getLog().info("Skipping morph.  Nothing has changed");
			return true;
		}
	}

	protected boolean isMorphNeeded() {
		// The file they asked to morph does not exist
		if (!getOldFile().exists()) {
			getLog().debug("file:" + getOldFile().getAbsolutePath() + " does not exist");
			return false;
		}

		// The new file does not exist, we need to morph
		if (!getNewFile().exists()) {
			return true;
		}

		// Extract the last modified timestamps
		long oldLastModified = getOldFile().lastModified();
		long newLastModified = getNewFile().lastModified();

		// If old file has been modified since the new file was last modified,
		// we need to morph
		return oldLastModified > newLastModified;
	}

	protected abstract Morpher getMorpher(MorphRequest request, String artifactId);

	@Override
	protected void executeMojo() throws MojoExecutionException {
		try {
			getLog().info("From: " + oldFile.getAbsolutePath());
			getLog().info("  To: " + newFile.getAbsolutePath());
			FileUtils.forceMkdir(new File(FileUtils.getPath(newFile.getAbsolutePath())));
			MorphRequest request = new MorphRequest(oldFile.getName(), new FileInputStream(oldFile), new FileOutputStream(newFile));
			request.setEncoding(getEncoding());
			Morpher morpher = getMorpher(request, artifactId);
			morpher.executeMorph();
		} catch (IOException e) {
			throw new MojoExecutionException("Unexpected error while attempting to morph " + oldFile.getAbsolutePath(), e);
		}
	}

	public File getNewFile() {
		return newFile;
	}

	public void setNewFile(final File newFile) {
		this.newFile = newFile;
	}

	public File getOldFile() {
		return oldFile;
	}

	public void setOldFile(final File oldFile) {
		this.oldFile = oldFile;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
}
