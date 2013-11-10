package org.kuali.common.util.channel.model;

public enum CopyDirection {

	TO_REMOTE, // We copied something (local file system, another url) to the remote server
	FROM_REMOTE; // We copied something from the remote sever to somewhere else (local file system, an output stream)

}
