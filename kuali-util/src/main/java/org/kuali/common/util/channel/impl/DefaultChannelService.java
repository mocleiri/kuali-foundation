package org.kuali.common.util.channel.impl;

import java.io.IOException;

import org.kuali.common.util.channel.api.ChannelService;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.ChannelContext;

/**
 * @deprecated
 */
@Deprecated
public class DefaultChannelService implements ChannelService {

	@Override
	public SecureChannel openChannel(ChannelContext context) throws IOException {
		return new DefaultSecureChannel(context);
	}

}
