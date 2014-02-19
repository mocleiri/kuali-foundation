package org.kuali.common.util.spring.convert.support;

import static org.kuali.common.util.base.Exceptions.illegalArgument;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

import org.kuali.common.util.Encodings;
import org.springframework.core.convert.converter.Converter;

public final class XmlStringToPropertiesConverter implements Converter<String, Properties> {

	public XmlStringToPropertiesConverter() {
		this(Encodings.UTF8);
	}

	public XmlStringToPropertiesConverter(String encoding) {
		this.encoding = checkNotBlank(encoding, "encoding");
	}

	private final String encoding;

	@Override
	public Properties convert(String source) {
		try {
			byte[] bytes = source.getBytes(encoding);
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			Properties props = new Properties();
			props.loadFromXML(in);
			return props;
		} catch (IOException e) {
			throw illegalArgument(e, "unexpected io error converting xml string into a properties object - encoding=[%s]\n\n%s\n\n", encoding, source);
		}
	}

	public String getEncoding() {
		return encoding;
	}
}
