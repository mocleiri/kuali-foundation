package org.kuali.common.devops.model;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Scm {

	private final String url;
	private final String revision;

	private Scm(Builder builder) {
		this.url = builder.url;
		this.revision = builder.revision;
	}

	public static Scm create(String url, String revision) {
		return builder().url(url).revision(revision).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Scm> {

		private String url;
		private String revision;

		public Builder url(String url) {
			this.url = url;
			return this;
		}

		public Builder revision(String revision) {
			this.revision = revision;
			return this;
		}

		@Override
		public Scm getInstance() {
			return new Scm(this);
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getRevision() {
			return revision;
		}

		public void setRevision(String revision) {
			this.revision = revision;
		}

	}

	public String getUrl() {
		return url;
	}

	public String getRevision() {
		return revision;
	}
}
