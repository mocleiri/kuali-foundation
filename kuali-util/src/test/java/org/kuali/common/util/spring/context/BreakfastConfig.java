package org.kuali.common.util.spring.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ CerealConfig.class, MilkConfig.class })
public class BreakfastConfig {

}
