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

import java.util.List;
import java.util.Properties;

public interface ProjectConfigService {

	/**
	 * Return a list of property locations associated with the id passed in.
	 * 
	 * <pre>
	 *  org.kuali.common:kuali-sql
	 *  org.kuali.common:kuali-util:scm
	 *  org.kuali.common:kuali-util:metainf:mpx
	 * </pre>
	 */
	List<Location> getLocations(String configId);

	/**
	 * Return a list of property locations associated with this groupId + artifactId
	 * 
	 * <pre>
	 *  org.kuali.common:kuali-util
	 * </pre>
	 */
	List<Location> getLocations(String groupId, String artifactId);

	/**
	 * Return a list of property locations associated with this groupId + artifactId + contextId
	 * 
	 * <pre>
	 *  org.kuali.common:kuali-util:metainf
	 * </pre>
	 */
	List<Location> getLocations(String groupId, String artifactId, String contextId);

	List<Location> getLocations(ConfigRequest request);

	List<Location> getLocations(List<ConfigRequest> requests);

	Properties getProperties(Properties project, List<ConfigRequest> requests);

	Properties getPropertiesFromIds(Properties project, List<String> configIds);

}
