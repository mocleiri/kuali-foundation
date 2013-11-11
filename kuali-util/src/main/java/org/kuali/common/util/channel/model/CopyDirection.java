package org.kuali.common.util.channel.model;

public enum CopyDirection {

	TO_REMOTE, // We copied something (a file on the local file system, the contents of a url, a java.lang.String object) to a file on the remote server
	FROM_REMOTE; // We copied a file from the remote sever to somewhere (our local file system, an output stream, a java.lang.String object)

}
