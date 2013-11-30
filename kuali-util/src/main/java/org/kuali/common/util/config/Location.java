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
package org.kuali.common.util.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Encodings;
import org.kuali.common.util.Mode;

/**
 * @deprecated
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Deprecated
public class Location {

	public static final Mode DEFAULT_MISSING_MODE = Mode.ERROR;
	public static final String DEFAULT_ENCODING = Encodings.UTF8;

	Mode missingMode = DEFAULT_MISSING_MODE;
	String encoding = DEFAULT_ENCODING;
	String value;

	public Location(Location location) {
		super();
		this.missingMode = location.getMissingMode();
		this.encoding = location.getEncoding();
		this.value = location.getValue();
	}

	public Location() {
		this((String) null);
	}

	public Location(String value) {
		this(value, DEFAULT_ENCODING, DEFAULT_MISSING_MODE);
	}

	public Location(String value, String encoding) {
		this(value, encoding, DEFAULT_MISSING_MODE);
	}

	public Location(String value, String encoding, Mode missingMode) {
		super();
		this.missingMode = missingMode;
		this.encoding = encoding;
		this.value = value;
	}

	@XmlAttribute(name = "missing")
	public Mode getMissingMode() {
		return missingMode;
	}

	@XmlAttribute
	public String getEncoding() {
		return encoding;
	}

	@XmlAttribute
	public String getValue() {
		return value;
	}

	public void setMissingMode(Mode missingMode) {
		this.missingMode = missingMode;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
