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
package org.kuali.common.util.execute;

import java.io.File;

public class CopyFileResult {

	protected static final boolean DEFAULT_OVERWRITTEN_VALUE = false;

	File source;
	File destination;
	boolean overwritten = DEFAULT_OVERWRITTEN_VALUE;

	public CopyFileResult() {
		this(null, null, DEFAULT_OVERWRITTEN_VALUE);
	}

	public CopyFileResult(File source, File destination, boolean overwritten) {
		super();
		this.source = source;
		this.destination = destination;
		this.overwritten = overwritten;
	}

	public File getDestination() {
		return destination;
	}

	public void setDestination(File destination) {
		this.destination = destination;
	}

	public boolean isOverwritten() {
		return overwritten;
	}

	public void setOverwritten(boolean overwritten) {
		this.overwritten = overwritten;
	}

}
