package org.kuali.common.jdbc.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcProjectConfig.class, JdbcCommonConfig.class, JdbcPropertiesConfig.class, JdbcDataSourceConfig.class, SqlDbaBeforeConfig.class, ResetSchemaConfig.class,
		ResetDataConfig.class, ResetConstraintsConfig.class, ResetOtherConfig.class })
public class ResetConfig {

}
