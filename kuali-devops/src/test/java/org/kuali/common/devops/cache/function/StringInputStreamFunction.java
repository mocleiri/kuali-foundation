package org.kuali.common.devops.cache.function;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.google.common.base.Function;

public class StringInputStreamFunction implements Function<String, InputStream> {

	public StringInputStreamFunction(String encoding) {
		this.encoding = checkNotBlank(encoding, "encoding");
	}

	private final String encoding;

	@Override
	public InputStream apply(String data) {
		checkNotNull(data);
		try {
			return new ByteArrayInputStream(data.getBytes(encoding));
		} catch (UnsupportedEncodingException e) {
			throw illegalState(e);
		}
	}

}
