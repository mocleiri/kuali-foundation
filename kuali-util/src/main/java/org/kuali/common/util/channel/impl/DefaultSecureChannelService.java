package org.kuali.common.util.channel.impl;

import java.io.IOException;

import org.kuali.common.util.channel.ChannelContext;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.api.SecureChannelService;

public class DefaultSecureChannelService implements SecureChannelService {

	@Override
	public SecureChannel getChannel(ChannelContext context) throws IOException {
		return new DefaultSecureChannel(context);
	}

}
