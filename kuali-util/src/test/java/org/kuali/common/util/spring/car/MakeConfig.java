package org.kuali.common.util.spring.car;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:${car.make}.properties")
public class MakeConfig {

}
