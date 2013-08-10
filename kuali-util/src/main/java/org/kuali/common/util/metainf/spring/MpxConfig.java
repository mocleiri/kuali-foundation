package org.kuali.common.util.metainf.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MpxContextsConfig.class, MetaInfExecutableConfig.class })
public class MpxConfig {
}
