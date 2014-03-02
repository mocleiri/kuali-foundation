package org.kuali.common.util.spring.convert.support;

import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.base.Exceptions.illegalArgument;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.core.convert.converter.Converter;

public final class PropertiesToXmlStringConverter implements Converter<Properties, String> {

	public PropertiesToXmlStringConverter() {
		this(UTF8);
	}

	public PropertiesToXmlStringConverter(String encoding) {
		this.encoding = checkNotBlank(encoding, "encoding");
	}

	private final String encoding;

	@Override
	public String convert(Properties props) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			props.storeToXML(out, null, encoding);
			return out.toString(encoding);
		} catch (IOException e) {
			throw illegalArgument(e, "unexpected io error converting properties object into an xml string - encoding=[%s]", encoding);
		}
	}

	public String getEncoding() {
		return encoding;
	}
}
