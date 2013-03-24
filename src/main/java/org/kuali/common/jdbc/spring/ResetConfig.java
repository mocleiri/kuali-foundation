package org.kuali.common.jdbc.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcCommonConfig.class, JdbcDbaConfig.class, JdbcProjectConfig.class, JdbcPropertiesConfig.class, ResetConstraintsConfig.class, ResetDataConfig.class,
		ResetDataSourceConfig.class, ResetDbaConfig.class, ResetOtherConfig.class, ResetSchemaConfig.class })
public class ResetConfig {

}
