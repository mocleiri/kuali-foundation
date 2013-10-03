/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.file;

import java.io.File;

public class MD5Result {

	public MD5Result(File source, String sourceChecksum, File target, String targetChecksum) {
		super();
		this.source = source;
		this.sourceChecksum = sourceChecksum;
		this.target = target;
		this.targetChecksum = targetChecksum;
	}

	private final File source;
	private final String sourceChecksum;
	private final File target;
	private final String targetChecksum;

	public File getSource() {
		return source;
	}

	public String getSourceChecksum() {
		return sourceChecksum;
	}

	public File getTarget() {
		return target;
	}

	public String getTargetChecksum() {
		return targetChecksum;
	}

}
