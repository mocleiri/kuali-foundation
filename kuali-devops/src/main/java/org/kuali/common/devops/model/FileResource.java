package org.kuali.common.devops.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.model.RemoteFile;

public final class FileResource {

	private final String title;
	private final RemoteFile file;
	private final String source;

	public static class Builder {

		// Required
		private final String title;
		private final RemoteFile file;
		private final String source;

		public Builder(String title, RemoteFile file, String source) {
			this.title = title;
			this.file = file;
			this.source = source;
		}

		public FileResource build() {
			Assert.noBlanks(title, source);
			Assert.noNulls(file);
			Assert.exists(source);
			return new FileResource(this);
		}
	}

	private FileResource(Builder builder) {
		this.title = builder.title;
		this.file = builder.file;
		this.source = builder.source;
	}

	public String getTitle() {
		return title;
	}

	public RemoteFile getFile() {
		return file;
	}

	public String getSource() {
		return source;
	}

}
