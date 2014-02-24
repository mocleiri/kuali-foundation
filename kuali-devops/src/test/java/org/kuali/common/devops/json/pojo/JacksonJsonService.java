package org.kuali.common.devops.json.pojo;

import static org.kuali.common.devops.json.pojo.JacksonContext.newJacksonJsonContext;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class JacksonJsonService implements JsonService {

	public JacksonJsonService() {
		this(newJacksonJsonContext());
	}

	public JacksonJsonService(JacksonContext context) {
		this.context = checkNotNull(context, "context");
	}

	private final JacksonContext context;

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
			return context.getMapper().readValue(in, valueType);
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
				context.getMapper().writer().withDefaultPrettyPrinter().writeValue(out, reference);
			} else {
				context.getMapper().writeValue(out, reference);
			}
		} catch (IOException e) {
			throw illegalState(e, "unexpected io error");
		}
	}

}
