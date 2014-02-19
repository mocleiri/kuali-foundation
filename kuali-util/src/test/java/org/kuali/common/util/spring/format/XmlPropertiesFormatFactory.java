package org.kuali.common.util.spring.format;

import java.util.Properties;

import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

public final class XmlPropertiesFormatFactory extends AbstractListStringFormatFactory<XmlPropertiesFormat> {

	@Override
	public Printer<Properties> getPrinter(XmlPropertiesFormat annotation, Class<?> fieldType) {
		return getFormatter(annotation);
	}

	@Override
	public Parser<Properties> getParser(XmlPropertiesFormat annotation, Class<?> fieldType) {
		return getFormatter(annotation);
	}

	protected Formatter<Properties> getFormatter(XmlPropertiesFormat annotation) {
		return new XmlPropertiesFormatter(annotation.encoding(), annotation.emptyPropertiesToken());
	}

}