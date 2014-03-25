package org.kuali.common.util.channel.model;

public enum CopyDirection {
	// We copied something (a file on the local file system, the contents of a url, a java.lang.String object) TO the file system on a remote server
	TO_REMOTE, //

	// We copied a file FROM the file system on a remote sever to somewhere (our local file system, an output stream, a java.lang.String object)
	FROM_REMOTE;

}
