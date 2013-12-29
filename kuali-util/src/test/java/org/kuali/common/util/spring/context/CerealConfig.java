package org.kuali.common.util.spring.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CerealConfig {

	@Autowired
	Milk milk;

	@Bean
	public Cereal cereal() {
		Cereal c = new Cereal();
		c.setName("chocolate cheerios");
		c.setPrice(2.50);
		return c;
	}

}
