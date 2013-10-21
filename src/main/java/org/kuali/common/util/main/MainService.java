package org.kuali.common.util.main;

import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.core.env.PropertySource;

public interface MainService {

	PropertySource<?> getPropertySource(MainContext context, Class<? extends PropertySourceConfig> config);

}
