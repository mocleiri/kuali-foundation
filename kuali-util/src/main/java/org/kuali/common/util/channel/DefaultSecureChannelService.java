package org.kuali.common.util.channel;

import java.io.IOException;

public class DefaultSecureChannelService implements SecureChannelService {

	@Override
	public SecureChannel getChannel(ChannelContext context) throws IOException {
		return new DefaultSecureChannel(context);
	}

}
