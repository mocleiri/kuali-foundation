package org.kuali.common.jdbc.context.spring;

import org.kuali.common.jdbc.service.spring.DatabaseVendorConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DatabaseVendorConfig.class })
public class JdbcContextConfig {

}
