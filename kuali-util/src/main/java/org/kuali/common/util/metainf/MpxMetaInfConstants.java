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
package org.kuali.common.util.metainf;

import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.KualiProjectConstants;
import org.kuali.common.util.config.ConfigConstants;
import org.kuali.common.util.config.ImmutableConfig;
import org.kuali.common.util.project.ImmutableProject;

public class MpxMetaInfConstants {

	private static final ImmutableProject PROJECT = KualiProjectConstants.KUALI_UTIL;

	public static final String MPX = "mpx";

	public static final String CONTEXT_ID = MetaInfConstants.METAINF + ":" + MPX;
	public static final ImmutableConfig CONFIG = new ImmutableConfig(PROJECT, CONTEXT_ID);
	public static final List<String> CONFIG_IDS = CollectionUtils.unmodifiableList(CONFIG.getId());

	public static final String BUILD_CONTEXT_ID = CONTEXT_ID + "," + ConfigConstants.BUILD;
	public static final ImmutableConfig BUILD_CONFIG = new ImmutableConfig(PROJECT, BUILD_CONTEXT_ID);
	public static final List<String> BUILD_CONFIG_IDS = CollectionUtils.unmodifiableList(BUILD_CONFIG.getId());

}
