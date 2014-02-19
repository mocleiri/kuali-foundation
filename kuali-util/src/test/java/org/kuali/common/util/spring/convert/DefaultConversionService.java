package org.kuali.common.util.spring.convert;

import java.io.File;

import org.kohsuke.MetaInfServices;
import org.kuali.common.util.spring.format.BytesFormatAnnotationFormatterFactory;
import org.kuali.common.util.spring.format.CanonicalFileFormatter;
import org.kuali.common.util.spring.format.OptionalStringFormatAnnotationFormatterFactory;
import org.kuali.common.util.spring.format.TimeFormatAnnotationFormatterFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

@MetaInfServices(ConversionService.class)
public class DefaultConversionService extends DefaultFormattingConversionService {

	public DefaultConversionService() {
		addFormatterForFieldAnnotation(new BytesFormatAnnotationFormatterFactory());
		addFormatterForFieldAnnotation(new TimeFormatAnnotationFormatterFactory());
		addFormatterForFieldAnnotation(new OptionalStringFormatAnnotationFormatterFactory());
		addFormatterForFieldType(File.class, new CanonicalFileFormatter());
		addConverter(new OptionalConverter());
	}

}
