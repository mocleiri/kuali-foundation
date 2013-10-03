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
package org.kuali.common.util.spring.config;

public abstract class SpringConfigConstants {

	/**
	 * This is the default profile Spring uses when no explicit default profile is provided.
	 */
	public static final String DEFAULT_PROFILE_ID = "default";

	/**
	 * The method to invoke on an executable via the @Execute annotation
	 */
	public static final String EXECUTABLE_INIT_METHOD = "execute";

}
