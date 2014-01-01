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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Morpher {
	private static final Log log = LogFactory.getLog(Morpher.class);

	MorphRequest morphRequest;
	String artifactId;

	public Morpher() {
		this(null, null);
	}

	public Morpher(MorphRequest morphRequest, String artifactId) {
		super();
		this.morphRequest = morphRequest;
		this.artifactId = artifactId;
	}

	protected abstract boolean isMorphNeeded(String contents);

	protected abstract String getMorphedContents(String contents);

	public void executeMorph() throws IOException {
		// Read the "old" data into a string
		InputStream in = morphRequest.getInputStream();
		String contents = IOUtils.toString(in, morphRequest.getEncoding());
		IOUtils.closeQuietly(in);

		// May not need to morph
		if (isMorphNeeded(contents)) {
			contents = getMorphedContents(contents);
		} else {
			log.debug("Skipping morph on " + morphRequest.getName());
		}

		// Write the morphed data to the output stream
		OutputStream out = morphRequest.getOutputStream();
		IOUtils.write(contents, out, morphRequest.getEncoding());
		IOUtils.closeQuietly(out);
	}

	public MorphRequest getMorphRequest() {
		return morphRequest;
	}

	public void setMorphRequest(MorphRequest morphRequest) {
		this.morphRequest = morphRequest;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String schema) {
		this.artifactId = schema;
	}

}
