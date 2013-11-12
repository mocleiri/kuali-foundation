package org.kuali.common.util.channel.impl;

import com.jcraft.jsch.ChannelExec;

public final class StreamHandler {

	public StreamHandler(StreamingCommandContext context, ChannelExec exec) {
		this.context = context;
		this.exec = exec;
	}

	private final StreamingCommandContext context;
	private final ChannelExec exec;

	public StreamingCommandContext getContext() {
		return context;
	}

	public ChannelExec getExec() {
		return exec;
	}

}
