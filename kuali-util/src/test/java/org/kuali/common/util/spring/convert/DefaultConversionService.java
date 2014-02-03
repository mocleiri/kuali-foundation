package org.kuali.common.util.spring.convert;

import java.io.File;

import org.kohsuke.MetaInfServices;
import org.kuali.common.util.spring.format.BytesFormatAnnotationFormatterFactory;
import org.kuali.common.util.spring.format.CanonicalFileFormatter;
import org.kuali.common.util.spring.format.OptionalIntegerFormatter;
import org.kuali.common.util.spring.format.OptionalLongFormatter;
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
		addFormatterForFieldType(Optional.class, new OptionalStringFormatter());
		addFormatterForFieldType(Optional.class, new OptionalIntegerFormatter());
		addFormatterForFieldType(Optional.class, new OptionalLongFormatter());
		addFormatterForFieldType(File.class, new CanonicalFileFormatter());
	}

}
