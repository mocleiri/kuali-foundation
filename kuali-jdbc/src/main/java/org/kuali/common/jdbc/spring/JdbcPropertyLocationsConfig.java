package org.kuali.common.jdbc.spring;

import org.kuali.common.util.properties.spring.PropertyLocationsCommonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ PropertyLocationsCommonConfig.class })
public class JdbcPropertyLocationsConfig {

}
