package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.model.RemoteFile;

public final class FileResource {

	public FileResource(String source, RemoteFile destination) {
		Assert.noBlanks(source);
		Assert.noNulls(destination);
		Assert.exists(source);
		this.source = source;
		this.destination = destination;
	}

	private final String source;
	private final RemoteFile destination;

	public String getSource() {
		return source;
	}

	public RemoteFile getDestination() {
		return destination;
	}

}
