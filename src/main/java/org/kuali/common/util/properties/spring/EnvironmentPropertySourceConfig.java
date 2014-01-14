package org.kuali.common.util.properties.spring;

import org.kuali.common.util.spring.PropertySourceUtils;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

@Configuration
public class EnvironmentPropertySourceConfig implements PropertySourceConfig {

    @Autowired
    ConfigurableEnvironment env;

    @Override
    @Bean
    public PropertySource<?> propertySource() {
        return new PropertiesPropertySource("environmentProperties", PropertySourceUtils.getAllEnumerablePropertiesQuietly(env));
    }

}


