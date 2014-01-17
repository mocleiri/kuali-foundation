package org.kuali.common.util.bind.impl;

import org.kuali.common.util.bind.api.BinderService;
import org.kuali.common.util.bind.api.Bound;
import org.kuali.common.util.spring.binder.BytesFormatAnnotationFormatterFactory;
import org.kuali.common.util.spring.binder.TimeFormatAnnotationFormatterFactory;
import org.kuali.common.util.spring.env.StandardEnvironment;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.MapBindingResult;

import com.google.common.collect.Maps;

public class EnvironmentBinderService implements BinderService {

	private final StandardEnvironment environment = new StandardEnvironment();
	private final ConversionService service = getConversionService();

	@Override
	public <T> BindingResult bind(T target) {
		if (!target.getClass().isAnnotationPresent(Bound.class)) {
			return new MapBindingResult(Maps.newHashMap(), DataBinder.DEFAULT_OBJECT_NAME);
		}
		EnvironmentDataBinder binder = new EnvironmentDataBinder(target);
		binder.setConversionService(service);
		binder.bind(environment);
		return binder.getBindingResult();
	}

	protected ConversionService getConversionService() {
		DefaultFormattingConversionService service = new DefaultFormattingConversionService();
		service.addFormatterForFieldAnnotation(new BytesFormatAnnotationFormatterFactory());
		service.addFormatterForFieldAnnotation(new TimeFormatAnnotationFormatterFactory());
		return service;
	}

}
