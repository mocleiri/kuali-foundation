package org.kuali.common.devops.logic;

import java.util.List;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class HttpCacherExecutable implements Executable {

	private final ImmutableList<String> urls;

	@Override
	public void execute() {

	}

	private HttpCacherExecutable(Builder builder) {
		this.urls = ImmutableList.copyOf(builder.urls);
	}

	public static class Builder extends ValidatingBuilder<HttpCacherExecutable> {

		private List<String> urls;

		public Builder urls(List<String> urls) {
			this.urls = urls;
			return this;
		}

		@Override
		public HttpCacherExecutable getInstance() {
			return new HttpCacherExecutable(this);
		}

		public List<String> getUrls() {
			return urls;
		}

		public void setUrls(List<String> urls) {
			this.urls = urls;
		}

	}

	public ImmutableList<String> getUrls() {
		return urls;
	}

}
