package org.kuali.common.util.spring.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:org/kuali/common/util/spring/context/breakfast.properties")
@ImportResource("classpath:org/kuali/common/util/spring/context/milk-context.xml")
public class MilkConfig {

}
