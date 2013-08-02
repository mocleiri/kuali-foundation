package org.kuali.common.util.metainf.spring;

import org.kuali.common.util.properties.spring.LocationPropertySourceConfig;
import org.kuali.common.util.properties.spring.ProjectPropertiesServiceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MpxBuildLocationsConfig.class, ProjectPropertiesServiceConfig.class })
public class MpxBuildPropertySourceConfig extends LocationPropertySourceConfig {

}
