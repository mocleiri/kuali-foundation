package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.model.RemoteFile;

public final class FileResource {

	public FileResource(String title, String source, RemoteFile destination) {
		Assert.noBlanks(title, source);
		Assert.noNulls(destination);
		Assert.exists(source);
		this.title = title;
		this.source = source;
		this.destination = destination;
	}

	private final String title;
	private final String source;
	private final RemoteFile destination;

	public String getSource() {
		return source;
	}

	public RemoteFile getDestination() {
		return destination;
	}

	public String getTitle() {
		return title;
	}

}
