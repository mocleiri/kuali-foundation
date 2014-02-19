package org.kuali.common.util.spring.convert;

import java.io.File;

import org.kohsuke.MetaInfServices;
import org.kuali.common.util.spring.convert.support.PropertiesToXmlStringConverter;
import org.kuali.common.util.spring.convert.support.XmlStringToPropertiesConverter;
import org.kuali.common.util.spring.format.BytesFormatFactory;
import org.kuali.common.util.spring.format.CanonicalFileFormatter;
import org.kuali.common.util.spring.format.FileListFormatFactory;
import org.kuali.common.util.spring.format.PathListFormatFactory;
import org.kuali.common.util.spring.format.TimeFormatFactory;
import org.kuali.common.util.spring.format.XmlPropertiesFormatFactory;
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
		addFormatterForFieldAnnotation(new XmlPropertiesFormatFactory());
		addFormatterForFieldType(File.class, new CanonicalFileFormatter());
		addConverter(new XmlStringToPropertiesConverter());
		addConverter(new PropertiesToXmlStringConverter());
	}

}
