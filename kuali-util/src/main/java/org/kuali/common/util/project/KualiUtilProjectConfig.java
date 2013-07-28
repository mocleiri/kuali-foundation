package org.kuali.common.util.project;

import org.kuali.common.util.project.spring.config.AutomaticProjectConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ KualiUtilProjectIdentifierConfig.class, AutomaticProjectConfig.class })
public class KualiUtilProjectConfig {

}
