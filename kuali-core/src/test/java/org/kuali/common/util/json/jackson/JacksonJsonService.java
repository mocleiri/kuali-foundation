package org.kuali.common.util.json.jackson;

import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.json.jackson.JacksonContext.newJacksonContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.kuali.common.util.json.api.JsonService;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JacksonJsonService implements JsonService {

	public JacksonJsonService() {
		this(newJacksonContext());
	}

	public JacksonJsonService(JacksonContext context) {
		this.context = checkNotNull(context, "context");
		if (context.isCopyObjectMapper()) {
			// Make our own defensive copy of the mapper so it's impossible for anything to alter
			// service behavior by messing with the mapper reference from the context object
			this.mapper = context.getMapper().copy();
		} else {
			this.mapper = context.getMapper();
		}
	}

	private final JacksonContext context;
	// ObjectMapper is mutable, don't expose it via a getter
	private final ObjectMapper mapper;

	@Override
	public <T> T readString(String json, Class<T> valueType) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(json.getBytes(UTF8));
			return read(in, valueType);
		} catch (IOException e) {
			throw illegalState(e, "unexpected io error");
		}
	}

	@Override
	public <T> T read(InputStream in, Class<T> valueType) {
		try {
			return mapper.readValue(in, valueType);
		} catch (IOException e) {
			throw illegalState(e, "unexpected io error");
		}
	}

	@Override
	public <T> String writeString(T reference) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		write(out, reference);
		try {
			return out.toString(UTF8);
		} catch (IOException e) {
			throw illegalState(e, "unexpected io error");
		}
	}

	@Override
	public <T> void write(OutputStream out, T reference) {
		try {
			if (context.isPrettyPrint()) {
				mapper.writer().withDefaultPrettyPrinter().writeValue(out, reference);
			} else {
				mapper.writeValue(out, reference);
			}
		} catch (IOException e) {
			throw illegalState(e, "unexpected io error");
		}
	}

	public JacksonContext getContext() {
		return context;
	}

}
