package org.kuali.common.util.spring.convert;

import org.springframework.core.convert.ConversionService;

public class Conversion {

	public synchronized static ConversionService getDefaultConversionService() {
		return null;
	}

	// private static ConversionService instance;
	// public synchronized static ConversionService getDefaultConversionService() {
	// if (instance == null) {
	// instance = ServiceProvider.getFirst(ConversionService.class);
	// }
	// return instance;
	// }

}
