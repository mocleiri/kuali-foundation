package org.kuali.common.devops.logic.function;

import static com.google.common.base.Optional.fromNullable;
import static org.apache.commons.lang.StringUtils.substringBetween;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;
import com.google.common.base.Optional;

@IdiotProofImmutable
public final class TomcatVersionFunction implements Function<String, Optional<String>> {

	private final String open;
	private final String close;

	@Override
	public Optional<String> apply(String content) {
		checkNotNull(content, "content");
		return fromNullable(substringBetween(content, open, close));
	}

	private TomcatVersionFunction(Builder builder) {
		this.open = builder.open;
		this.close = builder.close;
	}

	public static TomcatVersionFunction create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<TomcatVersionFunction> {

		private String open = "<h3>Apache Tomcat/";
		private String close = "</h3>";

		public Builder open(String open) {
			this.open = open;
			return this;
		}

		public Builder close(String close) {
			this.close = close;
			return this;
		}

		@Override
		public TomcatVersionFunction getInstance() {
			return new TomcatVersionFunction(this);
		}

		public String getOpen() {
			return open;
		}

		public void setOpen(String open) {
			this.open = open;
		}

		public String getClose() {
			return close;
		}

		public void setClose(String close) {
			this.close = close;
		}

	}

	public String getOpen() {
		return open;
	}

	public String getClose() {
		return close;
	}
}
