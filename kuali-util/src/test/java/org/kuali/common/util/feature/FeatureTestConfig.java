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
package org.kuali.common.util.feature;

import java.util.List;
import java.util.Map;

import org.kuali.common.util.config.ConfigMetadata;
import org.kuali.common.util.config.ConfigMetadataConfig;
import org.kuali.common.util.config.ConfigService;
import org.kuali.common.util.config.LocationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ ConfigMetadataConfig.class })
public class FeatureTestConfig {

	@Autowired
	ConfigMetadataConfig featureConfig;

	@Bean
	public Object whatup() {
		ConfigService service = featureConfig.utilFeatureService();
		Map<String, ConfigMetadata> features = featureConfig.utilFeatureMap();
		System.out.println(features.size());
		for (ConfigMetadata feature : features.values()) {
			List<LocationContext> contexts = feature.getLocationContexts();
			System.out.println(service.getId(feature));
			for (LocationContext context : contexts) {
				System.out.println(context.getLocation());
			}
			System.out.println();
		}
		return null;
	}
}
