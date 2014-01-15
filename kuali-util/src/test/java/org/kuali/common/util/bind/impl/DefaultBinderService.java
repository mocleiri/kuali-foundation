package org.kuali.common.util.bind.impl;

import java.util.Map;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.bind.api.BinderService;
import org.kuali.common.util.spring.binder.BytesFormatAnnotationFormatterFactory;
import org.kuali.common.util.spring.binder.TimeFormatAnnotationFormatterFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import com.google.common.collect.Maps;

public class DefaultBinderService implements BinderService {

	@Override
	public <T> BindingResult bind(T object) {
		Properties properties = PropertyUtils.getGlobalProperties();
		Map<String, String> map = Maps.newHashMap();
		for (String key : properties.stringPropertyNames()) {
			map.put(key, properties.getProperty(key));
		}
		DataBinder binder = new DataBinder(object);
		MutablePropertyValues pvs = new MutablePropertyValues(map);
		DefaultFormattingConversionService service = new DefaultFormattingConversionService();
		service.addFormatterForFieldAnnotation(new BytesFormatAnnotationFormatterFactory());
		service.addFormatterForFieldAnnotation(new TimeFormatAnnotationFormatterFactory());
		binder.setConversionService(service);
		binder.bind(pvs);
		return binder.getBindingResult();
	}

}
