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

public class ExecutionMetaData implements Comparable<ExecutionMetaData> {

	// The number of individual sql statements
	long count;

	// The collective size of the individual sql statements
	long size;

	// The collective time it took to execute the sql (in milliseconds)
	long millis;

	@Override
	public int compareTo(ExecutionMetaData other) {
		Long millis1 = this.millis;
		Long millis2 = other.getSize();
		return millis1.compareTo(millis2);
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getMillis() {
		return millis;
	}

	public void setMillis(long millis) {
		this.millis = millis;
	}

}
