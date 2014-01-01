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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileMorphRequest extends MorphRequest {
	File oldFile;
	File newFile;

	public FileMorphRequest(File oldFile, File newFile) {
		super();
		this.oldFile = oldFile;
		this.newFile = newFile;
	}

	public FileMorphRequest() {
		this(null, null);
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

	@Override
	public InputStream getInputStream() {
		try {
			return new FileInputStream(oldFile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public OutputStream getOutputStream() {
		try {
			return new FileOutputStream(newFile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
