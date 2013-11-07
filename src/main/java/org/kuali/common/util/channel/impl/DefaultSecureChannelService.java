package org.kuali.common.util.channel.impl;

import java.io.IOException;

import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.api.SecureChannelService;
import org.kuali.common.util.channel.model.ChannelContext;

public class DefaultSecureChannelService implements SecureChannelService {

	@Override
	public SecureChannel getChannel(ChannelContext context) throws IOException {
		return new DefaultSecureChannel(context);
	}

}
