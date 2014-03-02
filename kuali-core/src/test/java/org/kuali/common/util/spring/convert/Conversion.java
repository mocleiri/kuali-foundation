package org.kuali.common.util.spring.convert;

import org.kuali.common.util.serviceloader.ServiceProvider;
import org.springframework.core.convert.ConversionService;

public class Conversion {

	private static ConversionService instance;

	public synchronized static ConversionService getDefaultConversionService() {
		if (instance == null) {
			instance = ServiceProvider.getFirst(ConversionService.class);
		}
		return instance;
	}

}
