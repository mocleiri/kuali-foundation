package org.kuali.common.devops.json.pojo;

import static org.kuali.common.devops.json.pojo.JacksonContext.newJacksonJsonContext;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.base.Exceptions.illegalState;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class JacksonJsonService implements JsonService {

	private final JacksonContext context;

	@Override
	public <T> T readString(String json, Class<T> valueType) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(json.getBytes(UTF8));
			return read(in, valueType);
		} catch (IOException e) {
			throw illegalState("unexpected io error", e);
		}
	}

	@Override
	public <T> T read(InputStream in, Class<T> valueType) {
		try {
			return context.getMapper().readValue(in, valueType);
		} catch (IOException e) {
			throw illegalState("unexpected io error", e);
		}
	}

	@Override
	public <T> String writeString(T reference) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		write(out, reference);
		try {
			return out.toString(UTF8);
		} catch (IOException e) {
			throw illegalState("unexpected io error", e);
		}
	}

	@Override
	public <T> void write(OutputStream out, T reference) {
		try {
			context.getMapper().writeValue(out, reference);
		} catch (IOException e) {
			throw illegalState("unexpected io error", e);
		}
	}

	private JacksonJsonService(Builder builder) {
		this.context = builder.context;
	}

	public static JacksonJsonService newJacksonJsonService() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<JacksonJsonService> {

		private JacksonContext context = newJacksonJsonContext();

		@Override
		public JacksonJsonService build() {
			return validate(make());
		}

		@Override
		public Set<ConstraintViolation<JacksonJsonService>> violations() {
			return violations(make());
		}

		private JacksonJsonService make() {
			return new JacksonJsonService(this);
		}

		public Builder context(JacksonContext context) {
			this.context = context;
			return this;
		}

		public JacksonContext getContext() {
			return context;
		}

		public void setContext(JacksonContext context) {
			this.context = context;
		}

	}

}
