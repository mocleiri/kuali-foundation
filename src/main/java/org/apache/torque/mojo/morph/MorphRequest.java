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

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * 
 */
public class MorphRequest {

	String name;
	String encoding;
	InputStream inputStream;
	OutputStream outputStream;

	public MorphRequest() {
		this(null, null, null);
	}

	public MorphRequest(String filename, InputStream inputStream, OutputStream outputStream) {
		super();
		this.name = filename;
		this.inputStream = inputStream;
		this.outputStream = outputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream oldData) {
		this.inputStream = oldData;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream newData) {
		this.outputStream = newData;
	}

	public String getName() {
		return name;
	}

	public void setName(String filename) {
		this.name = filename;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
