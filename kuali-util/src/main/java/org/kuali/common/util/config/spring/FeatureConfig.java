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
package org.kuali.common.util.config.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.common.util.config.ConfigMetadata;
import org.kuali.common.util.config.ConfigService;
import org.kuali.common.util.config.DefaultConfigService;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FeatureConfig {

	protected static final String FEATURES_KEY = "feature.ids";
	protected static final String SERVICE_KEY = "feature.service";

	@Autowired
	Environment env;

	@Bean
	public ConfigService utilFeatureService() {
		return SpringUtils.getInstance(env, SERVICE_KEY, DefaultConfigService.class);
	}

	@Bean
	public Map<String, ConfigMetadata> utilFeatureMap() {
		List<String> ids = SpringUtils.getNoneSensitiveListFromCSV(env, FEATURES_KEY, Constants.NONE);
		ConfigService service = utilFeatureService();
		Map<String, ConfigMetadata> features = new HashMap<String, ConfigMetadata>();
		for (String id : ids) {
			ConfigMetadata feature = service.loadMetaData(id);
			features.put(id, feature);
		}
		return features;
	}
}
