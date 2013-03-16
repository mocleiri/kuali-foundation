package org.kuali.common.util.spring.car;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:car.properties")
public class CarConfig {

}
