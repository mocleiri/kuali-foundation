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
package org.kuali.common.util.nullify;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.property.Constants;

public class NullUtils {

	public static final boolean isNull(String s) {
		if (s == null) {
			return true;
		} else {
			return StringUtils.equalsIgnoreCase(Constants.NULL, s);
		}
	}

	public static final boolean isNone(String s) {
		return StringUtils.equalsIgnoreCase(Constants.NONE, s);
	}

	public static final boolean isNullOrNone(String s) {
		return isNull(s) || isNone(s);
	}
}
