package org.kuali.common.util.spring.format;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.util.Locale;
import java.util.Properties;

import org.kuali.common.util.spring.convert.support.StringToXmlPropertiesConverter;
import org.kuali.common.util.spring.convert.support.XmlPropertiesToStringConverter;
import org.springframework.format.Formatter;

public final class XmlPropertiesFormatter implements Formatter<Properties> {

	public XmlPropertiesFormatter(String encoding, String emptyPropertiesToken) {
		this.encoding = checkNotBlank(encoding, "encoding");
		this.emptyPropertiesToken = checkNotBlank(emptyPropertiesToken, "emptyPropertiesToken");
		this.parser = new StringToXmlPropertiesConverter(encoding);
		this.printer = new XmlPropertiesToStringConverter(encoding);
	}

	private final String encoding;
	private final String emptyPropertiesToken;
	private final StringToXmlPropertiesConverter parser;
	private final XmlPropertiesToStringConverter printer;

	@Override
	public String print(Properties properties, Locale locale) {
		if (properties.isEmpty()) {
			return emptyPropertiesToken;
		} else {
			return printer.convert(properties);
		}

	}

	@Override
	public Properties parse(String string, Locale locale) {
		if (emptyPropertiesToken.equals(string)) {
			return new Properties();
		} else {
			return parser.convert(string);
		}
	}

	public String getEncoding() {
		return encoding;
	}

	public StringToXmlPropertiesConverter getParser() {
		return parser;
	}

	public XmlPropertiesToStringConverter getPrinter() {
		return printer;
	}

	public String getEmptyPropertiesToken() {
		return emptyPropertiesToken;
	}

}
