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
package org.kuali.common.jdbc.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.config.ConfigRequest;
import org.kuali.common.util.config.ConfigUtils;

public abstract class JdbcConfigConstants {

	public static final List<? extends ConfigRequest> CONFIG_REQUESTS = Collections.unmodifiableList(Arrays.asList(new SqlConfigRequest(), new JdbcConfigRequest()));
	public static final List<String> CONFIG_IDS = Collections.unmodifiableList(ConfigUtils.getConfigIds(CONFIG_REQUESTS));

}
