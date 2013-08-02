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
package org.kuali.common.util.properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.property.PropertyFormat;

public final class ImmutableLocation extends DefaultLocation {

	private static final String UOE_MSG = "Immutable locations cannot be changed";

	public ImmutableLocation(String value) {
		super(value);
	}

	public ImmutableLocation(String value, String encoding) {
		super(value, encoding);
	}

	public ImmutableLocation(String value, String encoding, Mode missingMode, PropertyFormat format) {
		super(value, encoding, missingMode, format);
	}

	@Override
	public void setMissingMode(Mode missingMode) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void setEncoding(String encoding) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void setFormat(PropertyFormat format) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void setValue(String value) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

}
