package org.kuali.common.util.spring.convert;

import java.io.File;

import org.kohsuke.MetaInfServices;
import org.kuali.common.util.spring.convert.support.StringToXmlPropertiesConverter;
import org.kuali.common.util.spring.convert.support.XmlPropertiesToStringConverter;
import org.kuali.common.util.spring.format.BytesFormatFactory;
import org.kuali.common.util.spring.format.CanonicalFileFormatter;
import org.kuali.common.util.spring.format.FileListFormatFactory;
import org.kuali.common.util.spring.format.PathListFormatFactory;
import org.kuali.common.util.spring.format.TimeFormatFactory;
import org.kuali.common.util.spring.format.optional.OptionalStringFormatFactory;
import org.kuali.common.util.spring.format.optional.OptionalTimeZoneFormatFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

@MetaInfServices(ConversionService.class)
public class DefaultConversionService extends DefaultFormattingConversionService {

	public DefaultConversionService() {
		addFormatterForFieldAnnotation(new BytesFormatFactory());
		addFormatterForFieldAnnotation(new TimeFormatFactory());
		addFormatterForFieldAnnotation(new OptionalStringFormatFactory());
		addFormatterForFieldAnnotation(new OptionalTimeZoneFormatFactory());
		addFormatterForFieldAnnotation(new FileListFormatFactory());
		addFormatterForFieldAnnotation(new PathListFormatFactory());
		addFormatterForFieldType(File.class, new CanonicalFileFormatter());
		addConverter(new StringToXmlPropertiesConverter());
		addConverter(new XmlPropertiesToStringConverter());
	}

}
