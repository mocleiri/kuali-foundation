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
package org.kuali.common.jdbc.supplier;

import org.kuali.common.util.Str;
import org.kuali.common.util.nullify.NullUtils;

public class LocationSupplierUtils {

	/**
	 * Returns a location string with any prefix context information removed
	 * 
	 * The expected form of the parameter string is: CONTEXT:LOCATION
	 * 
	 * The return value of this method will return just the LOCATION token
	 * 
	 * @param contextLocation
	 *            the location string with prefixed context information
	 * @return a location string with no context prefix
	 */
	public static String getLocationFromContextLocation(String contextLocation) {
		// return every character after the first colon
		return contextLocation.substring(contextLocation.indexOf(Str.COLON) + 1);
	}

	/**
	 * Return just the context information prefix of a context location string
	 * 
	 * The expected form of the parameter string is: CONTEXT:LOCATION
	 * 
	 * The return value of this method will return just the CONTEXT string
	 * 
	 * @param contextLocation
	 *            the location string with prefixed context information
	 * @return a context token
	 */
	public static String getContextFromContextLocation(String contextLocation) {
		return Str.split(contextLocation, Str.COLON, true)[0];
	}

	/**
	 * Build a "context location" from a context and a location
	 * 
	 * The format of a context location is: CONTEXT:LOCATION
	 * 
	 * If no applicable context information is defined, the CONTEXT token will be set to Constants.NONE
	 * 
	 * @param context
	 *            object containing context information
	 * @param location
	 *            resource location string
	 * @return a formatted context location
	 */
	public static String getContextLocation(LocationSupplierContext context, String location) {
		String contextToken = context.getValue();

		if (contextToken == null) {
			contextToken = NullUtils.NONE;
		}

		return Str.getId(contextToken, location);
	}
}
