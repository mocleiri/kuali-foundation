package org.kuali.common.devops.logic;

import java.io.File;
import java.util.List;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class HttpCacherExecutable implements Executable {

	private final ImmutableList<String> urls;
	private final ImmutableList<File> files;

	@Override
	public void execute() {

	}

	private HttpCacherExecutable(Builder builder) {
		this.urls = ImmutableList.copyOf(builder.urls);
		this.files = ImmutableList.copyOf(builder.files);
	}

	public static class Builder extends ValidatingBuilder<HttpCacherExecutable> {

		private List<String> urls;
		private List<File> files;

		public Builder urls(List<String> urls) {
			this.urls = urls;
			return this;
		}

		public Builder files(List<File> files) {
			this.files = files;
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

		public List<File> getFiles() {
			return files;
		}

		public void setFiles(List<File> files) {
			this.files = files;
		}

	}

	public ImmutableList<String> getUrls() {
		return urls;
	}

	public ImmutableList<File> getFiles() {
		return files;
	}

}
