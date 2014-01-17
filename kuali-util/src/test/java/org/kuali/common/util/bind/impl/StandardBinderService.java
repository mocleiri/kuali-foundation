package org.kuali.common.util.bind.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import org.kuali.common.util.bind.api.BinderService;
import org.kuali.common.util.bind.api.Bound;
import org.kuali.common.util.spring.binder.BytesFormatAnnotationFormatterFactory;
import org.kuali.common.util.spring.binder.TimeFormatAnnotationFormatterFactory;
import org.kuali.common.util.spring.convert.Conversion;
import org.kuali.common.util.spring.env.Environments;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.MapBindingResult;

import com.google.common.collect.Maps;

public final class StandardBinderService implements BinderService {

	private final Environment environment;
	private final ConversionService service;

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

	public StandardBinderService() {
		this(builder());
		Builder.validate(this);
	}

	private StandardBinderService(Builder builder) {
		this.environment = builder.environment;
		this.service = builder.service;
	}

	public static StandardBinderService create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private Environment environment = Environments.getDefaultEnvironment();
		private ConversionService service = Conversion.getDefaultConversionService();

		public Builder environment(Environment environment) {
			this.environment = environment;
			return this;
		}

		public Builder service(ConversionService service) {
			this.service = service;
			return this;
		}

		public StandardBinderService build() {
			StandardBinderService instance = new StandardBinderService(this);
			validate(instance);
			return instance;
		}

		private static void validate(StandardBinderService instance) {
			checkNotNull(instance.environment, "'environment' cannot be null");
			checkNotNull(instance.service, "'service' cannot be null");
		}

		protected ConversionService getConversionService() {
			DefaultFormattingConversionService service = new DefaultFormattingConversionService();
			service.addFormatterForFieldAnnotation(new BytesFormatAnnotationFormatterFactory());
			service.addFormatterForFieldAnnotation(new TimeFormatAnnotationFormatterFactory());
			return service;
		}

	}

}
