package org.kuali.common.util.spring.convert;

import org.kohsuke.MetaInfServices;
import org.kuali.common.util.spring.format.BytesFormatAnnotationFormatterFactory;
import org.kuali.common.util.spring.format.CanonicalFileFormatAnnotationFormatterFactory;
import org.kuali.common.util.spring.format.OptionalStringFormatter;
import org.kuali.common.util.spring.format.TimeFormatAnnotationFormatterFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

import com.google.common.base.Optional;

@MetaInfServices(ConversionService.class)
public class DefaultConversionService extends DefaultFormattingConversionService {

	public DefaultConversionService() {
		addFormatterForFieldAnnotation(new BytesFormatAnnotationFormatterFactory());
		addFormatterForFieldAnnotation(new TimeFormatAnnotationFormatterFactory());
		addFormatterForFieldAnnotation(new CanonicalFileFormatAnnotationFormatterFactory());
		addFormatterForFieldType(Optional.class, new OptionalStringFormatter());
	}

}
