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
import java.util.Map;

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
		ConfigMetadataService service = featureConfig.utilConfigMetadataService();
		Map<String, ConfigMetadata> map = featureConfig.utilConfigMetadataMap();
		System.out.println(map.size());
		for (ConfigMetadata metadata : map.values()) {
			List<LocationContext> contexts = metadata.getLocationContexts();
			System.out.println(service.getId(metadata));
			for (LocationContext context : contexts) {
				System.out.println(context.getLocation());
			}
			System.out.println();
		}
		return null;
	}
}
