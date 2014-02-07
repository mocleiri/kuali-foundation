package org.kuali.common.devops.cache;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.kuali.common.util.Encodings.UTF8;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.kuali.common.util.base.Exceptions;

import com.google.common.base.Function;

public class StringInputStreamFunction implements Function<String, InputStream> {

	private final String encoding = UTF8;

	@Override
	public InputStream apply(String data) {
		checkNotNull(data);
		try {
			return new ByteArrayInputStream(data.getBytes(encoding));
		} catch (UnsupportedEncodingException e) {
			throw Exceptions.illegalState(e);
		}
	}

}
