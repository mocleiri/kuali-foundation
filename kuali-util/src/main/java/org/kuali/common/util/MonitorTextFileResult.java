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
package org.kuali.common.util;

public class MonitorTextFileResult {

	boolean exists;
	boolean contains;
	boolean timeoutExceeded;
	long elapsed;
	String absolutePath;
	String content;

	public MonitorTextFileResult() {
		this(false, false, false, -1);
	}

	public MonitorTextFileResult(boolean exists, boolean contains, boolean timeoutExceeded, long elapsed) {
		this.exists = exists;
		this.contains = contains;
		this.timeoutExceeded = timeoutExceeded;
		this.elapsed = elapsed;
	}

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public boolean isContains() {
		return contains;
	}

	public void setContains(boolean contains) {
		this.contains = contains;
	}

	public long getElapsed() {
		return elapsed;
	}

	public void setElapsed(long elapsed) {
		this.elapsed = elapsed;
	}

	public boolean isTimeoutExceeded() {
		return timeoutExceeded;
	}

	public void setTimeoutExceeded(boolean timeoutExceeded) {
		this.timeoutExceeded = timeoutExceeded;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

}
