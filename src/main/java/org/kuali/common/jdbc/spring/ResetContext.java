package org.kuali.common.jdbc.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JdbcCommon.class)
public class ResetContext {

}
