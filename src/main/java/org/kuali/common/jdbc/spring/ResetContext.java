package org.kuali.common.jdbc.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ResetCommon.class)
public class ResetContext {

}
