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
package org.kuali.common.aws.s3;

import org.kuali.common.util.FormatUtils;

public abstract class BucketConstants {

	public static final int DEFAULT_PREFIX_ESTIMATE = 100;
	public static final String DEFAULT_DELIMITER = "/";
	public static final int DEFAULT_MAX_KEYS = 1000;
	public static final int DEFAULT_MAX_LISTINGS = 10000;
	public static final int DEFAULT_LISTINGS_TIMEOUT_MILLIS = (int) FormatUtils.getMillis("10m");

}
