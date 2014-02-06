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
package org.kuali.common.util.config.service;

import java.util.List;
import java.util.Properties;

/**
 * @deprecated
 */
@Deprecated
public interface ConfigService {

	/**
	 * Load and return properties corresponding to <code>configIds</code>.
	 * 
	 * <pre>
	 *  org.kuali.common:kuali-sql
	 *  org.kuali.common:kuali-util:scm
	 *  org.kuali.common:kuali-util:metainf:mpx
	 * </pre>
	 */
	Properties getProperties(List<String> configIds);

	/**
	 * Load and return properties corresponding to <code>configId</code>.
	 * 
	 * <pre>
	 *  org.kuali.common:kuali-sql
	 *  org.kuali.common:kuali-util:scm
	 *  org.kuali.common:kuali-util:metainf:mpx
	 * </pre>
	 */
	Properties getProperties(String configId);

	/**
	 * Load and return properties corresponding to <code>configIds</code>, any properties from <code>overrides</code> "win" over properties loaded from the configuration.
	 * 
	 * <pre>
	 *  org.kuali.common:kuali-sql
	 *  org.kuali.common:kuali-util:scm
	 *  org.kuali.common:kuali-util:metainf:mpx
	 * </pre>
	 */
	Properties getProperties(List<String> configIds, Properties overrides);

	/**
	 * Load and return properties corresponding to <code>configId</code>, any properties from <code>overrides</code> "win" over properties loaded from the configuration.
	 * 
	 * <pre>
	 *  org.kuali.common:kuali-sql
	 *  org.kuali.common:kuali-util:scm
	 *  org.kuali.common:kuali-util:metainf:mpx
	 * </pre>
	 */
	Properties getProperties(String configId, Properties overrides);
}
