package org.kuali.common.util.spring.convert;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;

public final class BasicConversionServiceFactoryBean implements FactoryBean<ConversionService> {

	private final ConversionService service = new GenericConversionService();

	@Override
	public ConversionService getObject() {
		return service;
	}

	@Override
	public Class<? extends ConversionService> getObjectType() {
		return GenericConversionService.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
