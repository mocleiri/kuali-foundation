package org.kuali.common.util.metainf.spring;

import org.kuali.common.util.properties.spring.LocationPropertySourceConfig;
import org.kuali.common.util.properties.spring.ProjectPropertiesServiceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MpxLocationsConfig.class, ProjectPropertiesServiceConfig.class })
public class MpxPropertySourceConfig extends LocationPropertySourceConfig {

}
