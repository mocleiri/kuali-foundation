package org.kuali.common.devops.logic;

import java.util.List;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class URLCacher implements Executable {

	private final ImmutableList<String> urls;

	@Override
	public void execute() {
		for (String url : urls) {
			HttpCacher.cache(url);
		}
	}

	private URLCacher(Builder builder) {
		this.urls = ImmutableList.copyOf(builder.urls);
	}

	public static class Builder extends ValidatingBuilder<URLCacher> {

		private List<String> urls;

		public Builder urls(List<String> urls) {
			this.urls = urls;
			return this;
		}

		@Override
		public URLCacher getInstance() {
			return new URLCacher(this);
		}

		public List<String> getUrls() {
			return urls;
		}

		public void setUrls(List<String> urls) {
			this.urls = urls;
		}

	}

	public List<String> getUrls() {
		return urls;
	}

}
