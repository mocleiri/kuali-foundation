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
package org.kuali.common.util.metainf.model;

import org.kuali.common.util.Assert;

public class MetaInfResource implements Comparable<MetaInfResource> {

	public static final long UNKNOWN_SIZE = -1;
	public static final long UNKNOWN_LINECOUNT = -1;

	private final String location;
	private final long size;
	private final long lineCount;

	public MetaInfResource(String location) {
		this(location, UNKNOWN_SIZE, UNKNOWN_LINECOUNT);
	}

	public MetaInfResource(String location, long size, long lineCount) {
		Assert.noBlanks(location);
		this.location = location;
		this.size = size;
		this.lineCount = lineCount;
	}

	@Override
	public int compareTo(MetaInfResource other) {
		return location.compareTo(other.getLocation());
	}

	public String getLocation() {
		return location;
	}

	public long getSize() {
		return size;
	}

	public long getLineCount() {
		return lineCount;
	}

}
