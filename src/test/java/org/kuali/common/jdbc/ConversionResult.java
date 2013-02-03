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
package org.kuali.common.jdbc;

import java.io.File;
import java.util.List;

public class ConversionResult {

	File oldFile;
	File newFile;
	SqlMetaData before;
	SqlMetaData after;
	List<MorphResult> morphResults;

	public SqlMetaData getBefore() {
		return before;
	}

	public void setBefore(SqlMetaData before) {
		this.before = before;
	}

	public SqlMetaData getAfter() {
		return after;
	}

	public void setAfter(SqlMetaData after) {
		this.after = after;
	}

	public List<MorphResult> getMorphResults() {
		return morphResults;
	}

	public void setMorphResults(List<MorphResult> morphResults) {
		this.morphResults = morphResults;
	}

	public File getOldFile() {
		return oldFile;
	}

	public void setOldFile(File oldFile) {
		this.oldFile = oldFile;
	}

	public File getNewFile() {
		return newFile;
	}

	public void setNewFile(File newFile) {
		this.newFile = newFile;
	}

}
