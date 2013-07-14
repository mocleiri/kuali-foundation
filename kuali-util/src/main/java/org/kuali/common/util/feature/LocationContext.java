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
package org.kuali.common.util.feature;

import org.kuali.common.util.Mode;

public class LocationContext {

	public static final Mode DEFAULT_MISSING_MODE = Mode.ERROR;
	public static final String DEFAULT_ENCODING = "UTF-8";

	Mode missingMode = DEFAULT_MISSING_MODE;
	String encoding = DEFAULT_ENCODING;
	String location;

	public LocationContext() {
		this(null);
	}

	public LocationContext(String location) {
		this(location, DEFAULT_ENCODING, DEFAULT_MISSING_MODE);
	}

	public LocationContext(String location, String encoding) {
		this(location, encoding, DEFAULT_MISSING_MODE);
	}

	public LocationContext(String location, String encoding, Mode missingMode) {
		super();
		this.missingMode = missingMode;
		this.encoding = encoding;
		this.location = location;
	}

	public Mode getMissingMode() {
		return missingMode;
	}

	public void setMissingMode(Mode missingMode) {
		this.missingMode = missingMode;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
