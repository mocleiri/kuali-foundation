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
package org.apache.torque.mojo.morph;

import org.codehaus.plexus.util.StringUtils;

public class DataMorpher extends Morpher {
	// Ant prologue
	String antPrologue = "<?xml version=\"1.0\"?>";
	// New prologue
	String newPrologue = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	// The hard coded Ant impex document type
	String antDocType = "<!DOCTYPE dataset SYSTEM \"data.dtd\">";

	public DataMorpher() {
		this(null, null);
	}

	public DataMorpher(MorphRequest morphRequest, String artifactId) {
		super(morphRequest, artifactId);
	}

	protected String getNewDocType() {
		return "<!DOCTYPE dataset SYSTEM \"" + getArtifactId() + ".dtd\">";
	}

	protected String getMorphedContents(String contents) {
		contents = StringUtils.replace(contents, antPrologue, newPrologue);
		return StringUtils.replace(contents, antDocType, getNewDocType());
	}

	/**
	 * Return true if we need to morph this file
	 */
	protected boolean isMorphNeeded(String contents) {
		// Look for the DTD the Maven Impex Plugin uses
		int pos = contents.indexOf(getNewDocType());

		if (pos == -1) {
			// It isn't there so we should morph
			return true;
		} else {
			// It is already there, we are good to go
			return false;
		}
	}

	public String getAntPrologue() {
		return antPrologue;
	}

	public void setAntPrologue(String antPrologue) {
		this.antPrologue = antPrologue;
	}

	public String getNewPrologue() {
		return newPrologue;
	}

	public void setNewPrologue(String newPrologue) {
		this.newPrologue = newPrologue;
	}

}
